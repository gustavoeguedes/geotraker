package br.com.gustavoeguedes.geotraker.controller.dto;

import java.time.LocalDateTime;

public record DateRange(LocalDateTime inicio, LocalDateTime fim) {
}