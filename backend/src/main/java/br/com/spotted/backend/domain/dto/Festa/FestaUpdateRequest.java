package br.com.spotted.backend.domain.dto.Festa;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class FestaUpdateRequest {

    @NotEmpty(message = "Campo nome é obrigatório!")
    private String tituloArtefato;

    @NotEmpty(message = "Campo 'descrição' é obrigatório!")
    private String descricaoArtefato;

    @NotEmpty
    private String localizacaoFesta;
}
