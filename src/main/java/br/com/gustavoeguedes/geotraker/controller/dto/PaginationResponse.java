package br.com.gustavoeguedes.geotraker.controller.dto;

public record PaginationResponse(int number, int size, long totalElements, int totalPages) {
}
