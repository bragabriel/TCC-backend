package br.com.spotted.backend.domain.dto.Usuario;

import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.Usuario;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private String url;
    private Long sequence;
    private String fileName;
    private List<Artefato> listaArtefatos;

    private List<ArtefatoResponse> listaArtefatosReponse;

    // Usado para mapear criar um DTO usando uma entidade
    public UsuarioResponse(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nomeUsuario = usuario.getNomeUsuario();
        this.sobrenomeUsuario = usuario.getSobrenomeUsuario();
        this.emailUsuario = usuario.getEmailUsuario();
        this.telefoneUsuario = usuario.getTelefoneUsuario();
        this.dataNascimento = usuario.getDataNascimento();
        this.url = usuario.getUrl();
        this.sequence = usuario.getSequence();
        this.fileName = usuario.getFileName();
        this.listaArtefatosReponse = usuario.getListaArtefatos().stream()
                .map(artefato -> new ArtefatoResponse(artefato))
                .collect(Collectors.toList());
    }
}