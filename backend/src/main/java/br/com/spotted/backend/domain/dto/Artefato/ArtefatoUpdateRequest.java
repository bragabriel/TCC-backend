package br.com.spotted.backend.domain.dto.Artefato;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ArtefatoUpdateRequest {
    @NotEmpty(message = "Campo titulo é obrigatório!")
    private String tituloArtefato;

    @NotEmpty(message = "Campo descrição é obrigatório!")
    private String descricaoArtefato;

    @NotEmpty(message = "Campo tipo é obrigatório!")
    private String tipoArtefato;

    @NotNull(message = "Campo ativo é obrigatório!")
    private Boolean ativo;

    @NotEmpty(message = "Campo data é obrigatório!")
    private Date dataCadastro;
}
