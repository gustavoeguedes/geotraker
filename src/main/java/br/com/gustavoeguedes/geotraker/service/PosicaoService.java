package br.com.gustavoeguedes.geotraker.service;

import br.com.gustavoeguedes.geotraker.controller.dto.CreatePosicaoDto;
import br.com.gustavoeguedes.geotraker.controller.dto.DateRange;
import br.com.gustavoeguedes.geotraker.controller.dto.PosicaoDto;
import br.com.gustavoeguedes.geotraker.exception.InvalidDateRangeException;
import br.com.gustavoeguedes.geotraker.entity.PosicaoVeiculo;
import br.com.gustavoeguedes.geotraker.exception.ResourceNotFoundException;
import br.com.gustavoeguedes.geotraker.repository.PosicaoVeiculoRepository;
import br.com.gustavoeguedes.geotraker.repository.VeiculoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PosicaoService {
    private final PosicaoVeiculoRepository posicaoVeiculoRepository;
    private final VeiculoRepository veiculoRepository;
    private final GeoService geoService;
    private final VeiculoService veiculoService;

    public PosicaoService(PosicaoVeiculoRepository posicaoVeiculoRepository,
                          VeiculoRepository veiculoRepository,
                          GeoService geoService,
                          VeiculoService veiculoService) {
        this.posicaoVeiculoRepository = posicaoVeiculoRepository;
        this.veiculoRepository = veiculoRepository;
        this.geoService = geoService;
        this.veiculoService = veiculoService;
    }

    public PosicaoVeiculo registerPosicao(CreatePosicaoDto dto, UUID veiculoId) {
        var veiculoEntity = veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com id: " + veiculoId));

        PosicaoVeiculo posicaoEntity = new PosicaoVeiculo();
        posicaoEntity.setVeiculo(veiculoEntity);
        posicaoEntity.setLatitude(dto.latitude());
        posicaoEntity.setLongitude(dto.longitude());

        if (dto.dataHora() == null) {
            posicaoEntity.setDataHora(LocalDateTime.now());
        } else {
            posicaoEntity.setDataHora(dto.dataHora());
        }

        return posicaoVeiculoRepository.save(posicaoEntity);
    }

    public Page<PosicaoDto> getHistorico(UUID veiculoId, LocalDateTime inicio, LocalDateTime fim, Integer page, Integer pageSize) {
        veiculoService.veiculoExitsById(veiculoId);
        DateRange dateRange = validateDateRange(inicio, fim);

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "dataHora"));

        return posicaoVeiculoRepository.findByVeiculoIdAndDataHoraBetween(veiculoId, dateRange.inicio(), dateRange.fim(), pageable).map(PosicaoDto::fromEntity);

    }

    public List<PosicaoDto> getTrajetoria(UUID veiculoId, LocalDateTime inicio, LocalDateTime fim) {
        veiculoService.veiculoExitsById(veiculoId);
        DateRange dateRange = validateDateRange(inicio, fim);

        List<PosicaoVeiculo> posicoes = posicaoVeiculoRepository.findByVeiculoIdAndDataHoraBetweenOrderByDataHoraAsc(veiculoId, dateRange.inicio(), dateRange.fim());

        return posicoes.stream()
                .map(PosicaoDto::fromEntity)
                .toList();
    }

    public Double getDistanciaPercorrida(UUID veiculoId, LocalDateTime inicio, LocalDateTime fim) {
        veiculoService.veiculoExitsById(veiculoId);
        DateRange dateRange = validateDateRange(inicio, fim);

        List<PosicaoVeiculo> posicoes = posicaoVeiculoRepository.findByVeiculoIdAndDataHoraBetweenOrderByDataHoraAsc(veiculoId, dateRange.inicio(), dateRange.fim());

        if (posicoes.size() < 2) {
            return 0.0;
        }

        double distanciaTotal = 0.0;


        for (int i = 0; i < posicoes.size() - 1; i++) {
            PosicaoVeiculo atual = posicoes.get(i);
            PosicaoVeiculo proximo = posicoes.get(i + 1);

            distanciaTotal += geoService.calcularDistanciaKm(
                    atual.getLatitude().doubleValue(),
                    atual.getLongitude().doubleValue(),
                    proximo.getLatitude().doubleValue(),
                    proximo.getLongitude().doubleValue()
            );
        }

        return distanciaTotal;
    }

    public Long getTempoDeslocamento(UUID veiculoId, LocalDateTime inicio, LocalDateTime fim) {
        veiculoService.veiculoExitsById(veiculoId);
        DateRange dateRange = validateDateRange(inicio, fim);

        List<PosicaoVeiculo> posicoes = posicaoVeiculoRepository.findByVeiculoIdAndDataHoraBetweenOrderByDataHoraAsc(veiculoId, dateRange.inicio(), dateRange.fim());

        if (posicoes.isEmpty()) {
            return 0L;
        }

        PosicaoVeiculo primeira = posicoes.get(0);
        PosicaoVeiculo ultima = posicoes.get(posicoes.size() - 1);

        Duration duracao = Duration.between(primeira.getDataHora(), ultima.getDataHora());

        return duracao.toMinutes();
    }

    private DateRange validateDateRange(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio != null && fim == null) {
            fim = inicio.plusDays(7);
        } else if (inicio == null && fim != null) {
            inicio = fim.minusDays(7);
        } else if (inicio == null) {
            fim = LocalDateTime.now();
            inicio = fim.minusDays(7);
        }

        if (inicio.isAfter(fim)) {
            throw new InvalidDateRangeException("Parâmetro 'inicio' deve ser anterior ou igual a 'fim'.");
        }

        return new DateRange(inicio, fim);
    }
}