package br.com.gustavoeguedes.geotraker.controller;

import br.com.gustavoeguedes.geotraker.controller.dto.CreateGeofenceDto;
import br.com.gustavoeguedes.geotraker.controller.dto.GeofenceDto;
import br.com.gustavoeguedes.geotraker.service.GeofenceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/geofences")
public class GeofenceController {

    private final GeofenceService geofenceService;

    public GeofenceController(GeofenceService geofenceService) {
        this.geofenceService = geofenceService;
    }

    @PostMapping
    public ResponseEntity<GeofenceDto> criar(@Valid @RequestBody CreateGeofenceDto dto) {
        GeofenceDto geofence = geofenceService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(geofence);
    }

    @GetMapping
    public ResponseEntity<List<GeofenceDto>> listar() {
        return ResponseEntity.ok(geofenceService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeofenceDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(geofenceService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable UUID id) {
        geofenceService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}