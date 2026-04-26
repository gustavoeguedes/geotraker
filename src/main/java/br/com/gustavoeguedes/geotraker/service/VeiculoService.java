package br.com.gustavoeguedes.geotraker.service;

import br.com.gustavoeguedes.geotraker.controller.dto.CreateVeiculoDto;
import br.com.gustavoeguedes.geotraker.controller.dto.VeiculoDto;
import br.com.gustavoeguedes.geotraker.controller.dto.EditVeiculoDto;
import br.com.gustavoeguedes.geotraker.entity.Veiculo;
import br.com.gustavoeguedes.geotraker.exception.ResourceNotFoundException;
import br.com.gustavoeguedes.geotraker.exception.VeiculoExistsException;
import br.com.gustavoeguedes.geotraker.repository.VeiculoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo create(CreateVeiculoDto dto) {
        veiculoRepository.findByPlaca(dto.placa())
                .ifPresent(_ -> {
                    throw new VeiculoExistsException("Veículo com placa " + dto.placa() + " já existe.");
                });

        var veiculoEntity = new Veiculo();

        veiculoEntity.setAno(dto.ano());
        veiculoEntity.setCor(dto.cor());
        veiculoEntity.setMarca(dto.marca());
        veiculoEntity.setPlaca(dto.placa());
        veiculoEntity.setModelo(dto.modelo());

        return veiculoRepository.save(veiculoEntity);
    }

    public VeiculoDto findById(UUID id) {
        var veiculoEntity = veiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com id: " + id));

        return VeiculoDto.fromEntity(veiculoEntity);
    }

    public VeiculoDto findByPlaca(String placa) {
        var veiculoEntity = veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com placa: " + placa));

        return VeiculoDto.fromEntity(veiculoEntity);
    }

    public VeiculoDto editVeiculo(UUID id, EditVeiculoDto dto) {
        var veiculoEntity = veiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com id: " + id));

        if (dto.placa() != null && !dto.placa().equals(veiculoEntity.getPlaca())) {
            veiculoRepository.findByPlaca(dto.placa())
                    .ifPresent(_ -> {
                        throw new VeiculoExistsException("Veículo com placa " + dto.placa() + " já existe.");
                    });
            veiculoEntity.setPlaca(dto.placa());
        }

        if (dto.modelo() != null) {
            veiculoEntity.setModelo(dto.modelo());
        }
        if (dto.marca() != null) {
            veiculoEntity.setMarca(dto.marca());
        }
        if (dto.ano() != null) {
            veiculoEntity.setAno(dto.ano());
        }
        if (dto.cor() != null) {
            veiculoEntity.setCor(dto.cor());
        }

        return VeiculoDto.fromEntity(veiculoRepository.save(veiculoEntity));
    }

    public void deleteById(UUID id) {
        veiculoRepository.deleteById(id);
    }

    public Page<VeiculoDto> findAll(int page, int size) {
        var pageable = PageRequest.of(page, size);
        return veiculoRepository.findAll(pageable)
                .map(VeiculoDto::fromEntity);
    }
}