package br.com.spotted.backend.domain.dto;

import lombok.Data;

@Data
public class PaginatedSearchRequest {
    private Integer PaginaAtual;
    private Integer QtdPorPagina;
}

