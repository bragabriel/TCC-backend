package br.com.spotted.backend.domain.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
public class PaginatedSearchRequest {
    private Integer PaginaAtual;
    private Integer QtdPorPagina;

    public PageRequest parseToPageRequest(){
        return PageRequest.of(this.getPaginaAtual()-1, this.getQtdPorPagina());
    }
}

