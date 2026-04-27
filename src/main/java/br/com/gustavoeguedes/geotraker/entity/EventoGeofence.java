package br.com.gustavoeguedes.geotraker.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "eventos_geofence")
public class EventoGeofence {

    public enum TipoEvento {
        ENTRADA,
        SAIDA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoEvento tipo;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "geofence_id", nullable = false)
    private Geofence geofence;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    public EventoGeofence() {
    }

    public EventoGeofence(TipoEvento tipo, Veiculo veiculo, Geofence geofence) {
        this.tipo = tipo;
        this.veiculo = veiculo;
        this.geofence = geofence;
        this.dataHora = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Geofence getGeofence() {
        return geofence;
    }

    public void setGeofence(Geofence geofence) {
        this.geofence = geofence;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}