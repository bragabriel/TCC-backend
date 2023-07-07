package br.com.spotted.backend.domain.dto.Emprego;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoCreateRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EmpregoCreateRequest {

    @NotEmpty
    private String localizacaoEmprego;

    @NotEmpty
    private String requisitosEmprego;

    @NotNull
    private Integer salarioEmprego;

    @NotEmpty
    private String beneficiosEmprego;

    @NotNull
    private ArtefatoCreateRequest artefato;
}
