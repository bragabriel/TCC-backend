package br.com.spotted.backend.domain.dto.Usuario;

import br.com.spotted.backend.domain.entity.Image;
import br.com.spotted.backend.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioResponse {

    private Long idUsuario;
    private String nomeUsuario;
    private String sobrenomeUsuario;
    private String emailUsuario;
    private String telefoneUsuario;
    private Date dataNascimento;
    private String descricaoUsuario;
    private byte[] imagemUsuario;

    // Usado para mapear criar um DTO usando uma entidade
    public UsuarioResponse(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nomeUsuario = usuario.getNomeUsuario();
        this.sobrenomeUsuario = usuario.getSobrenomeUsuario();
        this.emailUsuario = usuario.getEmailUsuario();
        this.telefoneUsuario = usuario.getTelefoneUsuario();
        this.dataNascimento = usuario.getDataNascimento();
        this.imagemUsuario = usuario.getImagemUsuario();
        this.descricaoUsuario = usuario.getDescricaoUsuario();
    }
}
