package br.com.gustavoeguedes.geotraker.service;

import br.com.gustavoeguedes.geotraker.controller.dto.EventoGeofenceDto;
import br.com.gustavoeguedes.geotraker.entity.EventoGeofence;
import br.com.gustavoeguedes.geotraker.entity.Geofence;
import br.com.gustavoeguedes.geotraker.entity.Veiculo;
import br.com.gustavoeguedes.geotraker.repository.EventoGeofenceRepository;
import br.com.gustavoeguedes.geotraker.repository.GeofenceRepository;
import br.com.gustavoeguedes.geotraker.repository.VeiculoRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class MonitoramentoService {

    private final GeofenceRepository geofenceRepository;
    private final EventoGeofenceRepository eventoGeofenceRepository;
    private final VeiculoRepository veiculoRepository;
    private final GeoService geoService;

    public MonitoramentoService(GeofenceRepository geofenceRepository,
                              EventoGeofenceRepository eventoGeofenceRepository,
                              VeiculoRepository veiculoRepository,
                              GeoService geoService) {
        this.geofenceRepository = geofenceRepository;
        this.eventoGeofenceRepository = eventoGeofenceRepository;
        this.veiculoRepository = veiculoRepository;
        this.geoService = geoService;
    }

    @Async
    public void verificarPosicao(UUID veiculoId) {
        Veiculo veiculo = veiculoRepository.findById(veiculoId).orElse(null);
        if (veiculo == null || veiculo.getUltimaLatitude() == null) {
            return;
        }

        List<Geofence> geofencesAtivas = geofenceRepository.findAll().stream()
                .filter(Geofence::getAtivo)
                .toList();

        for (Geofence geofence : geofencesAtivas) {
            boolean dentro = verificarSeEstaDentro(geofence, veiculo.getUltimaLatitude(), veiculo.getUltimaLongitude());
            boolean estavaAntes = verificarEstadoAnterior(veiculoId, geofence.getId());

            if (dentro && !estavaAntes) {
                registrarEvento(EventoGeofence.TipoEvento.ENTRADA, veiculo, geofence);
            } else if (!dentro && estavaAntes) {
                registrarEvento(EventoGeofence.TipoEvento.SAIDA, veiculo, geofence);
            }
        }
    }

    private boolean verificarSeEstaDentro(Geofence geofence, Double latitude, Double longitude) {
        double distancia = geoService.calcularDistanciaKm(latitude, longitude,
                geofence.getLatitudeCentro(), geofence.getLongitudeCentro());
        double raioKm = geofence.getRaio() / 1000.0;
        return distancia <= raioKm;
    }

    private boolean verificarEstadoAnterior(UUID veiculoId, UUID geofenceId) {
        List<EventoGeofence> ultimosEventos = eventoGeofenceRepository.findByVeiculoIdAndGeofenceIdOrderByDataHoraDesc(veiculoId, geofenceId);
        if (ultimosEventos.isEmpty()) {
            return false;
        }
        return ultimosEventos.get(0).getTipo() == EventoGeofence.TipoEvento.ENTRADA;
    }

    private void registrarEvento(EventoGeofence.TipoEvento tipo, Veiculo veiculo, Geofence geofence) {
        EventoGeofence evento = new EventoGeofence(tipo, veiculo, geofence);
        eventoGeofenceRepository.save(evento);
    }

    public List<EventoGeofenceDto> listarEventos(UUID veiculoId) {
        return eventoGeofenceRepository.findByVeiculoIdOrderByDataHoraDesc(veiculoId).stream()
                .map(EventoGeofenceDto::fromEntity)
                .toList();
    }
}