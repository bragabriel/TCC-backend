package br.com.spotted.backend.domain.dto.Objeto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ObjetoUpdateRequest {

    @NotEmpty(message = "Campo nome é obrigatório!")
    private String tituloArtefato;

    @NotEmpty(message = "Campo 'descrição' é obrigatório!")
    private String descricaoArtefato;

    @NotEmpty
    private String localizacaoAchadoObjeto;

    @NotEmpty
    private String localizacaoAtualObjeto;
}
