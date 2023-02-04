package br.com.spotted.backend.domain.dto.Comida;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ComidaCreateRequest {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String tipo;

    private byte[] imagemComida = null;

    @NotNull
    private Long idUsuario;
}
