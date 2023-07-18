package br.com.spotted.backend.domain.dto.Artefato;

import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.Imagem;
import br.com.spotted.backend.domain.entity.TipoArtefato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtefatoIndividualResponse {

    private Long idArtefato;
    private String tituloArtefato;
    private String descricaoArtefato;
    private String tipoArtefato;
    private Boolean ativo;
    private Date dataCadastro;
    private Date dataAtualizacao;
    private Long idUsuario;
    private List<Imagem> listaImagens;

    public ArtefatoIndividualResponse(Artefato artefato) {
        this.idArtefato = artefato.getIdArtefato();
        this.tituloArtefato = artefato.getTituloArtefato();
        this.descricaoArtefato = artefato.getDescricaoArtefato();
        this.tipoArtefato = artefato.getTipoArtefato();
        this.ativo = artefato.getAtivo();
        this.dataCadastro = artefato.getDataCadastro();
        this.dataAtualizacao = artefato.getDataAtualizacao();
        this.idUsuario = artefato.getIdUsuario();
        this.listaImagens = artefato.getListaImagens();
    }
}
