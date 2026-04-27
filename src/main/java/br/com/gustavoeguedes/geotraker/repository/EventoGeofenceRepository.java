package br.com.gustavoeguedes.geotraker.repository;

import br.com.gustavoeguedes.geotraker.entity.EventoGeofence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventoGeofenceRepository extends JpaRepository<EventoGeofence, UUID> {
    List<EventoGeofence> findByVeiculoIdOrderByDataHoraDesc(UUID veiculoId);
    List<EventoGeofence> findByVeiculoIdAndGeofenceIdOrderByDataHoraDesc(UUID veiculoId, UUID geofenceId);
}