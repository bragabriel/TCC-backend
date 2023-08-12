package br.com.spotted.backend.domain.dto.Festa;

import lombok.Data;

@Data
public class FestaUpdateRequest {

    private String tituloArtefato;

    private String descricaoArtefato;

    private String localizacaoFesta;
}
