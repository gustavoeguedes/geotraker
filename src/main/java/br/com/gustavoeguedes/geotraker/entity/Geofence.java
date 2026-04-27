package br.com.gustavoeguedes.geotraker.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "geofences")
public class Geofence {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private GeofenceTipo tipo;

    @Column(name = "latitude_centro", nullable = false)
    private Double latitudeCentro;

    @Column(name = "longitude_centro", nullable = false)
    private Double longitudeCentro;

    @Column(name = "raio", nullable = false)
    private Double raio;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    public Geofence() {
    }

    public Geofence(String nome, GeofenceTipo tipo, Double latitudeCentro, Double longitudeCentro, Double raio) {
        this.nome = nome;
        this.tipo = tipo;
        this.latitudeCentro = latitudeCentro;
        this.longitudeCentro = longitudeCentro;
        this.raio = raio;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public GeofenceTipo getTipo() {
        return tipo;
    }

    public void setTipo(GeofenceTipo tipo) {
        this.tipo = tipo;
    }

    public Double getLatitudeCentro() {
        return latitudeCentro;
    }

    public void setLatitudeCentro(Double latitudeCentro) {
        this.latitudeCentro = latitudeCentro;
    }

    public Double getLongitudeCentro() {
        return longitudeCentro;
    }

    public void setLongitudeCentro(Double longitudeCentro) {
        this.longitudeCentro = longitudeCentro;
    }

    public Double getRaio() {
        return raio;
    }

    public void setRaio(Double raio) {
        this.raio = raio;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}