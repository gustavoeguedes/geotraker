package br.com.gustavoeguedes.geotraker.controller.dto;

import br.com.gustavoeguedes.geotraker.entity.GeofenceTipo;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateGeofenceDto(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Tipo é obrigatório")
        GeofenceTipo tipo,

        @NotNull(message = "Latitude do centro é obrigatória")
        @DecimalMin(value = "-90.0", message = "Latitude deve estar entre -90 e 90")
        @DecimalMax(value = "90.0", message = "Latitude deve estar entre -90 e 90")
        Double latitudeCentro,

        @NotNull(message = "Longitude do centro é obrigatória")
        @DecimalMin(value = "-180.0", message = "Longitude deve estar entre -180 e 180")
        @DecimalMax(value = "180.0", message = "Longitude deve estar entre -180 e 180")
        Double longitudeCentro,

        @NotNull(message = "Raio é obrigatório")
        @DecimalMin(value = "50.0", message = "Raio mínimo é 50 metros")
        @DecimalMax(value = "10000.0", message = "Raio máximo é 10 quilômetros")
        Double raio
) {
}