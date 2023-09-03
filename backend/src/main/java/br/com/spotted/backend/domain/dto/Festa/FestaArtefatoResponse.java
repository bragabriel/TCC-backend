package br.com.spotted.backend.domain.dto.Festa;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoIndividualResponse;
import br.com.spotted.backend.domain.entity.Festa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FestaArtefatoResponse extends ArtefatoIndividualResponse {

    private Long idArtefato;

    private String localizacaoFesta;

    public FestaArtefatoResponse(Festa festa) {
        super();
        this.idArtefato = festa.getIdArtefato();
        this.localizacaoFesta = festa.getLocalizacaoFesta();
        this.setTituloArtefato(festa.getArtefato().getTituloArtefato());
        this.setDescricaoArtefato(festa.getArtefato().getDescricaoArtefato());
        this.setAtivo(festa.getArtefato().getAtivo());
        this.setTipoArtefato(festa.getArtefato().getTipoArtefato());
        this.setDataCadastro(festa.getArtefato().getDataCadastro().toString());

        if (festa.getArtefato() != null && festa.getArtefato().getDataAtualizacao() != null) {
            this.setDataAtualizacao(festa.getArtefato().getDataAtualizacao().toString());
        } else {
            this.setDataAtualizacao("");
        }

        this.setIdUsuario(festa.getArtefato().getIdUsuario());
        this.setTelefoneUsuario(festa.getArtefato().getUsuario().getTelefoneUsuario());
        this.setListaImagens(festa.getArtefato().getListaImagens());
    }
}