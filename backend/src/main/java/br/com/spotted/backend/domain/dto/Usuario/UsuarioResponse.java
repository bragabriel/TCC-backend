package br.com.spotted.backend.domain.dto.Usuario;

import br.com.spotted.backend.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioResponse {

    private Long idUsuario;

    private String nomeUsuario;

    private String emailUsuario;

    private String senhaUsuario;

    private Date dataNascimento;

    // Usado para mapear criar um DTO usando uma entidade
    public UsuarioResponse(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nomeUsuario = usuario.getNomeUsuario();
        this.emailUsuario = usuario.getEmailUsuario();
        this.dataNascimento = usuario.getDataNascimento();
    }
}
