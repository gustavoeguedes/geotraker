package br.com.gustavoeguedes.geotraker.controller.dto;

import br.com.gustavoeguedes.geotraker.entity.PosicaoVeiculo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PosicaoDto(
        UUID id,
        UUID veiculoId,
        BigDecimal latitude,
        BigDecimal longitude,
        LocalDateTime dataHora
) {
    public static PosicaoDto fromEntity(PosicaoVeiculo posicaoVeiculo) {
        return new PosicaoDto(
                posicaoVeiculo.getId(),
                posicaoVeiculo.getVeiculo().getId(),
                posicaoVeiculo.getLatitude(),
                posicaoVeiculo.getLongitude(),
                posicaoVeiculo.getDataHora()
        );
    }
}
