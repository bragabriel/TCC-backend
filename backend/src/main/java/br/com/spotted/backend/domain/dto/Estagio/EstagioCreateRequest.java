package br.com.spotted.backend.domain.dto.Estagio;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EstagioCreateRequest {

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

    private Long idUsuario;
}
