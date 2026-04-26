package br.com.gustavoeguedes.geotraker.controller;


import br.com.gustavoeguedes.geotraker.controller.dto.ApiResponse;
import br.com.gustavoeguedes.geotraker.controller.dto.CreateVeiculoDto;
import br.com.gustavoeguedes.geotraker.controller.dto.EditVeiculoDto;
import br.com.gustavoeguedes.geotraker.controller.dto.VeiculoDto;
import br.com.gustavoeguedes.geotraker.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = "/veiculos")
public class VeiculoController {
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<Void> createVeiculo(@RequestBody @Valid CreateVeiculoDto dto) {
        var veiculoEntity = veiculoService.create(dto);
        var uri = URI.create("/veiculos/" + veiculoEntity.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<VeiculoDto>> getAllVeiculos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        var resp = veiculoService.findAll(page, pageSize);
        return ResponseEntity.ok(ApiResponse.of(resp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDto> getVeiculoById(@PathVariable String id) {
        var resp = veiculoService.findById(java.util.UUID.fromString(id));
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<VeiculoDto> getVeiculoByPlaca(@PathVariable String placa) {
        var resp = veiculoService.findByPlaca(placa);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDto> editVeiculo(@PathVariable String id, @RequestBody @Valid EditVeiculoDto dto) {
        var resp = veiculoService.editVeiculo(UUID.fromString(id), dto);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable String id) {
        veiculoService.deleteById(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
