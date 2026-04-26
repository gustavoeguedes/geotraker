package br.com.gustavoeguedes.geotraker.controller.dto;

import br.com.gustavoeguedes.geotraker.controller.dto.validator.AnoValido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateVeiculoDto(
        
        @NotBlank(message = "Placa é obrigatória")
        @Pattern(regexp = "^[A-Z]{3}-[0-9]{4}$|^[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{1}$", 
                 message = "Placa deve seguir formato brasileiro (XXX-0000 ou XXX0A00)")
        String placa,
        
        @NotBlank(message = "Modelo é obrigatório")
        String modelo,
        
        @NotBlank(message = "Marca é obrigatória")
        String marca,
        
        @NotNull(message = "Ano é obrigatório")
        @AnoValido(message = "Ano do veículo deve ser entre 1900 e o ano atual")
        Integer ano,
        
        @NotBlank(message = "Cor é obrigatória")
        String cor) {

}