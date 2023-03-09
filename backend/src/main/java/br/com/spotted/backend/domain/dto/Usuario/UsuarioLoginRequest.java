package br.com.spotted.backend.domain.dto.Usuario;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UsuarioLoginRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String senha;
}
