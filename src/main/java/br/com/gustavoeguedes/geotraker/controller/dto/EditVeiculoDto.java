package br.com.gustavoeguedes.geotraker.controller.dto;

import br.com.gustavoeguedes.geotraker.controller.dto.validator.AnoValido;
import jakarta.validation.constraints.Pattern;

public record EditVeiculoDto(

        @Pattern(regexp = "^[A-Z]{3}-[0-9]{4}$|^[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{1}$",
                message = "Placa deve seguir formato brasileiro (XXX-0000 ou XXX0A00)")
        String placa,

        String modelo,

        String marca,

        @AnoValido(message = "Ano do veículo deve ser entre 1900 e o ano atual")
        Integer ano,

        String cor) {
}