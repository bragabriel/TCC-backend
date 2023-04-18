package br.com.spotted.backend.domain.dto.Festa;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class FestaUpdateRequest {

    @NotEmpty
    private String tituloFesta;

    @NotEmpty
    private String descricaoFesta;

    @NotEmpty
    private String localizacaoFesta;
}
