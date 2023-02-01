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
    private String sobrenomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private String telefoneUsuario;
    private Date dataNascimento;

    private byte[] imagemUsuario;

    // Usado para mapear criar um DTO usando uma entidade
    public UsuarioResponse(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nomeUsuario = usuario.getNomeUsuario();
        this.sobrenomeUsuario = usuario.getSobrenomeUsuario();
        this.emailUsuario = usuario.getEmailUsuario();
        this.senhaUsuario = usuario.getSenhaUsuario();
        this.telefoneUsuario = usuario.getTelefoneUsuario();
        this.dataNascimento = usuario.getDataNascimento();
        this.imagemUsuario = usuario.getImagemUsuario();
    }
}
