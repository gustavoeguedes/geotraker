package br.com.gustavoeguedes.geotraker.controller.dto;

import br.com.gustavoeguedes.geotraker.controller.dto.validator.AnoValido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record VeiculoDto(
        UUID id,
        String placa,
        String modelo,
        String marca,
        Integer ano,
        String cor) {

    public static VeiculoDto fromEntity(br.com.gustavoeguedes.geotraker.entity.Veiculo veiculo) {
        return new VeiculoDto(
                veiculo.getId(),
                veiculo.getPlaca(),
                veiculo.getModelo(),
                veiculo.getMarca(),
                veiculo.getAno(),
                veiculo.getCor()
        );
    }
}
