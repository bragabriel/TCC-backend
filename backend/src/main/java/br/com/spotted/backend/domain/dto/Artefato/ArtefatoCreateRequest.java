package br.com.spotted.backend.domain.dto.Artefato;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ArtefatoCreateRequest {

    @NotEmpty
    private String tituloArtefato;

    @NotEmpty
    private String descricaoArtefato;

    @NotEmpty
    private String tipoArtefato;

    @NotNull
    private Boolean ativo;

    @NotEmpty
    private String dataCadastro;
}
