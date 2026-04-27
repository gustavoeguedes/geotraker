package br.com.gustavoeguedes.geotraker.service;

import br.com.gustavoeguedes.geotraker.controller.dto.CreateGeofenceDto;
import br.com.gustavoeguedes.geotraker.controller.dto.GeofenceDto;
import br.com.gustavoeguedes.geotraker.entity.Geofence;
import br.com.gustavoeguedes.geotraker.exception.ResourceNotFoundException;
import br.com.gustavoeguedes.geotraker.repository.GeofenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GeofenceService {

    private static final double RAIO_MINIMO_METROS = 50.0;
    private static final double RAIO_MAXIMO_METROS = 10000.0;

    private final GeofenceRepository geofenceRepository;
    private final GeoService geoService;

    public GeofenceService(GeofenceRepository geofenceRepository, GeoService geoService) {
        this.geofenceRepository = geofenceRepository;
        this.geoService = geoService;
    }

    public GeofenceDto criar(CreateGeofenceDto dto) {
        Geofence geofence = new Geofence();
        geofence.setNome(dto.nome());
        geofence.setTipo(dto.tipo());
        geofence.setLatitudeCentro(dto.latitudeCentro());
        geofence.setLongitudeCentro(dto.longitudeCentro());
        geofence.setRaio(dto.raio());
        geofence.setAtivo(true);

        Geofence salvo = geofenceRepository.save(geofence);
        return GeofenceDto.fromEntity(salvo);
    }

    public List<GeofenceDto> listar() {
        return geofenceRepository.findAll().stream()
                .map(GeofenceDto::fromEntity)
                .toList();
    }

    public GeofenceDto buscarPorId(UUID id) {
        Geofence geofence = geofenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Geofence não encontrada com id: " + id));
        return GeofenceDto.fromEntity(geofence);
    }

    public void inativar(UUID id) {
        Geofence geofence = geofenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Geofence não encontrada com id: " + id));
        geofence.setAtivo(false);
        geofenceRepository.save(geofence);
    }

    public boolean verificarSeVeiculoEstaDentro(UUID geofenceId, Double latitude, Double longitude) {
        Geofence geofence = geofenceRepository.findById(geofenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Geofence não encontrada com id: " + geofenceId));

        if (!geofence.getAtivo()) {
            return false;
        }

        double distancia = geoService.calcularDistanciaKm(
                latitude,
                longitude,
                geofence.getLatitudeCentro(),
                geofence.getLongitudeCentro()
        );

        double raioKm = geofence.getRaio() / 1000.0;

        return distancia <= raioKm;
    }
}