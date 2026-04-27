package br.com.gustavoeguedes.geotraker.controller.dto;

import br.com.gustavoeguedes.geotraker.entity.Geofence;
import br.com.gustavoeguedes.geotraker.entity.GeofenceTipo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GeofenceDto(
        UUID id,
        String nome,
        GeofenceTipo tipo,
        Double latitudeCentro,
        Double longitudeCentro,
        Double raio,
        Boolean ativo
) {
    public static GeofenceDto fromEntity(Geofence geofence) {
        return new GeofenceDto(
                geofence.getId(),
                geofence.getNome(),
                geofence.getTipo(),
                geofence.getLatitudeCentro(),
                geofence.getLongitudeCentro(),
                geofence.getRaio(),
                geofence.getAtivo()
        );
    }
}