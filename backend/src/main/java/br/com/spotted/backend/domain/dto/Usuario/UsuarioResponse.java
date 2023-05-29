package br.com.spotted.backend.domain.dto.Usuario;

import br.com.spotted.backend.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

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

    private String url;

    private Long sequence;

    private String fileName;

    // Usado para mapear criar um DTO usando uma entidade
    public UsuarioResponse(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nomeUsuario = usuario.getNomeUsuario();
        this.sobrenomeUsuario = usuario.getSobrenomeUsuario();
        this.emailUsuario = usuario.getEmailUsuario();
        this.telefoneUsuario = usuario.getTelefoneUsuario();
        this.dataNascimento = usuario.getDataNascimento();
        this.descricaoUsuario = usuario.getDescricaoUsuario();
        this.url = usuario.getUrl();
        this.sequence = usuario.getSequence();
        this.fileName = usuario.getFileName();
    }
}
