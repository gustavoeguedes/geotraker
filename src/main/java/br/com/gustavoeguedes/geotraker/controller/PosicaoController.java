package br.com.gustavoeguedes.geotraker.controller;

import br.com.gustavoeguedes.geotraker.controller.dto.ApiResponse;
import br.com.gustavoeguedes.geotraker.controller.dto.CreatePosicaoDto;
import br.com.gustavoeguedes.geotraker.controller.dto.EditPosicaoDto;
import br.com.gustavoeguedes.geotraker.controller.dto.PosicaoDto;
import br.com.gustavoeguedes.geotraker.service.PosicaoService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/veiculos")
public class PosicaoController {
    private final PosicaoService posicaoService;

    public PosicaoController(PosicaoService posicaoService) {
        this.posicaoService = posicaoService;
    }

    @PostMapping("/{veiculoId}/posicoes")
    public ResponseEntity<Void> registerPosicao(@PathVariable UUID veiculoId, @RequestBody @Valid CreatePosicaoDto dto) {
        var posicaoEntity = posicaoService.registerPosicao(dto,veiculoId);
        var uri = URI.create("/veiculos/" + posicaoEntity.getVeiculo().getId() + "/posicoes/" + posicaoEntity.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{veiculoId}/posicao")
    public ResponseEntity<PosicaoDto> atualizarPosicao(@PathVariable UUID veiculoId, @RequestBody @Valid EditPosicaoDto dto) {
        return ResponseEntity.ok(posicaoService.atualizarPosicao(veiculoId, dto));
    }

    @GetMapping("/{veiculoId}/posicoes")
    public ResponseEntity<ApiResponse<PosicaoDto>> getHistorico(@PathVariable UUID veiculoId,
                                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
                                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                                @RequestParam(defaultValue = "0") Integer page){

        var resp = posicaoService.getHistorico(veiculoId, dataInicio, dataFim, page, pageSize);

        return ResponseEntity.ok(ApiResponse.of(resp));

    }

    @GetMapping("/{veiculoId}/trajetoria")
    public ResponseEntity<List<PosicaoDto>> getTrajetoria(@PathVariable UUID veiculoId,
                                                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim){
        var trajetoriaPage = posicaoService.getTrajetoria(veiculoId, dataInicio, dataFim);

        return ResponseEntity.ok(trajetoriaPage);
    }

    @GetMapping("/{veiculoId}/distancia-percorrida")
    public ResponseEntity<Double> getDistanciaPercorrida(@PathVariable UUID veiculoId,
                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim){
        var distanciaPercorrida = posicaoService.getDistanciaPercorrida(veiculoId, dataInicio, dataFim);

        return ResponseEntity.ok(distanciaPercorrida);
    }

    @GetMapping("/{veiculoId}/tempo-deslocamento")
    public ResponseEntity<Long> getTempoDeslocamento(@PathVariable UUID veiculoId,
                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim){
        var tempoDeslocamentoInMinutes = posicaoService.getTempoDeslocamento(veiculoId, dataInicio, dataFim);

        return ResponseEntity.ok(tempoDeslocamentoInMinutes);
    }



}
