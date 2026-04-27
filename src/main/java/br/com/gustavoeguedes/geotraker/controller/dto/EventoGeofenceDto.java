package br.com.gustavoeguedes.geotraker.controller.dto;

import br.com.gustavoeguedes.geotraker.entity.EventoGeofence;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventoGeofenceDto(
        UUID id,
        EventoGeofence.TipoEvento tipo,
        UUID veiculoId,
        UUID geofenceId,
        String geofenceNome,
        LocalDateTime dataHora
) {
    public static EventoGeofenceDto fromEntity(EventoGeofence evento) {
        return new EventoGeofenceDto(
                evento.getId(),
                evento.getTipo(),
                evento.getVeiculo().getId(),
                evento.getGeofence().getId(),
                evento.getGeofence().getNome(),
                evento.getDataHora()
        );
    }
}