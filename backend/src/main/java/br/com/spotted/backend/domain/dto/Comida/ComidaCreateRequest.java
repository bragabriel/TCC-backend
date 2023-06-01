package br.com.spotted.backend.domain.dto.Comida;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ComidaCreateRequest {

    @NotEmpty
    private String tituloComida;

    @NotEmpty
    private String descricaoComida;

    @NotEmpty
    private String tipoComida;

    @NotEmpty
    private String marcaComida;

    @NotNull
    private Double precoComida;

    @NotEmpty
    private String ofertaComida;

    @NotNull
    private Long idUsuario;
}
