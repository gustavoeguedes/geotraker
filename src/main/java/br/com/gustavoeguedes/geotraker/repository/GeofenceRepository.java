package br.com.gustavoeguedes.geotraker.repository;

import br.com.gustavoeguedes.geotraker.entity.Geofence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GeofenceRepository extends JpaRepository<Geofence, UUID> {
}
