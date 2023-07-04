package br.com.spotted.backend.domain.dto.Emprego;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoCreateRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EmpregoCreateRequest {

    @NotEmpty
    private String localizacaoEstagio;

    @NotEmpty
    private String requisitosEstagio;

    @NotNull
    private Integer salarioEstagio;

    @NotEmpty
    private String beneficiosEstagio;

    @NotNull
    private ArtefatoCreateRequest artefato;
}
