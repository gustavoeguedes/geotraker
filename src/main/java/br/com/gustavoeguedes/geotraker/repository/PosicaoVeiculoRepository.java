package br.com.gustavoeguedes.geotraker.repository;

import br.com.gustavoeguedes.geotraker.entity.PosicaoVeiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PosicaoVeiculoRepository extends JpaRepository<PosicaoVeiculo, UUID> {
    Page<PosicaoVeiculo> findByVeiculoIdAndDataHoraBetween(UUID veiculoId, LocalDateTime inicio, LocalDateTime fim, Pageable pageable);
    Page<PosicaoVeiculo> findByVeiculoId(UUID veiculoId, Pageable pageable);
    Page<PosicaoVeiculo> findByVeiculoIdAndDataHoraBetweenOrderByDataHoraAsc(UUID veiculoId, LocalDateTime inicio, LocalDateTime fim, Pageable pageable);
    List<PosicaoVeiculo> findByVeiculoIdAndDataHoraBetweenOrderByDataHoraAsc(UUID veiculoId, LocalDateTime inicio, LocalDateTime fim);
}
