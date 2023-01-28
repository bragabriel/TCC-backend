package br.com.spotted.backend.domain.dto.Usuario;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
public class UsuarioCreateRequest {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String sobrenome;

    @NotEmpty
    private String email;

    @NotEmpty
    private String senha;

    @NotEmpty
    private String telefone;

    @NotNull
    private Date dataNascimento;
}


