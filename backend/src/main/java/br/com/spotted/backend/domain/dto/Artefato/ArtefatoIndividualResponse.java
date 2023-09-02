package br.com.spotted.backend.domain.dto.Artefato;

import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.Imagem;
import br.com.spotted.backend.domain.entity.TipoArtefato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtefatoIndividualResponse {

    private Long idArtefato;
    private String tituloArtefato;
    private String descricaoArtefato;
    private Boolean ativo;
    private TipoArtefato tipoArtefato;
    private String dataCadastro;
    private String dataAtualizacao;
    private Long idUsuario;
    private String telefoneUsuario;
    private List<Imagem> listaImagens;

    public ArtefatoIndividualResponse(Artefato artefato) {
        this.idArtefato = artefato.getIdArtefato();
        this.tituloArtefato = artefato.getTituloArtefato();
        this.descricaoArtefato = artefato.getDescricaoArtefato();
        this.ativo = artefato.getAtivo();
        this.tipoArtefato = artefato.getTipoArtefato();
        this.dataCadastro = artefato.getDataCadastro().toString();
        this.dataAtualizacao = artefato.getDataAtualizacao().toString();
        this.idUsuario = artefato.getIdUsuario();
        this.telefoneUsuario = artefato.getUsuario().getTelefoneUsuario();
        this.listaImagens = artefato.getListaImagens();
    }
}
