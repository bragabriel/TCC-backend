package br.com.spotted.backend.domain.dto.Objeto;

import lombok.Data;

@Data
public class ObjetoUpdateRequest {

    private String tituloArtefato;

    private String descricaoArtefato;

    private String localizacaoAchadoObjeto;

    private String localizacaoAtualObjeto;
}
