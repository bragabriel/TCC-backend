package br.com.spotted.backend.domain.dto.Evento;

import lombok.Data;

@Data
public class EventoUpdateRequest {

    private String tituloArtefato;

    private String descricaoArtefato;

    private String localizacaoEvento;
}
