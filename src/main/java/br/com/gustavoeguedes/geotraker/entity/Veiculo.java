package br.com.gustavoeguedes.geotraker.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "placa", nullable = false, unique = true)
    private String placa;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @Column(name = "cor", nullable = false)
    private String cor;

    @Column(name = "ultima_latitude")
    private Double ultimaLatitude;

    @Column(name = "ultima_longitude")
    private Double ultimaLongitude;

    @Column(name = "ultima_posicao_data_hora")
    private LocalDateTime ultimaPosicaoDataHora;


    public Veiculo() {
    }

    public Veiculo(UUID id, String marca, String placa, String modelo, Integer ano, String cor) {
        this.id = id;
        this.marca = marca;
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getUltimaLatitude() {
        return ultimaLatitude;
    }

    public void setUltimaLatitude(Double ultimaLatitude) {
        this.ultimaLatitude = ultimaLatitude;
    }

    public Double getUltimaLongitude() {
        return ultimaLongitude;
    }

    public void setUltimaLongitude(Double ultimaLongitude) {
        this.ultimaLongitude = ultimaLongitude;
    }

    public LocalDateTime getUltimaPosicaoDataHora() {
        return ultimaPosicaoDataHora;
    }

    public void setUltimaPosicaoDataHora(LocalDateTime ultimaPosicaoDataHora) {
        this.ultimaPosicaoDataHora = ultimaPosicaoDataHora;
    }
}