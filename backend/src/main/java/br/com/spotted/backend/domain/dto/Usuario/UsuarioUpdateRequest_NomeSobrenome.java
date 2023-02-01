package br.com.spotted.backend.domain.dto.Usuario;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UsuarioUpdateRequest_NomeSobrenome {

    @NotEmpty(message = "Nome é obrigatório!")
    private String nomeUsuario;

    @NotEmpty(message = "Sobrenome é obrigatório!")
    private String sobrenomeUsuario;

}
