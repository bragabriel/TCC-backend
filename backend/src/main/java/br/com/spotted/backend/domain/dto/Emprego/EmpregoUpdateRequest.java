package br.com.spotted.backend.domain.dto.Emprego;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EmpregoUpdateRequest {
    @NotEmpty
    private String tituloEstagio;

    @NotEmpty
    private String descricaoEstagio;

    @NotEmpty
    private String localizacaoEstagio;

    @NotEmpty
    private String requisitosEstagio;

    @NotNull
    private Integer salarioEstagio;

    @NotEmpty
    private String beneficiosEstagio;
}
