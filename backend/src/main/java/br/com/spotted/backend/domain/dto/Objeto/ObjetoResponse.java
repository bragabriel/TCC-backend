package br.com.spotted.backend.domain.dto.Objeto;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoIndividualResponse;
import br.com.spotted.backend.domain.entity.Objeto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ObjetoResponse extends ArtefatoIndividualResponse {

    private Long idArtefato;

    private String localizacaoAchadoObjeto;

    private String localizacaoAtualObjeto;


    public ObjetoResponse(Objeto objeto) {
        this.idArtefato = objeto.getIdArtefato();
        this.localizacaoAchadoObjeto = objeto.getLocalizacaoAchadoObjeto();
        this.localizacaoAtualObjeto = objeto.getLocalizacaoAtualObjeto();

        this.setTituloArtefato(objeto.getArtefato().getTituloArtefato());
        this.setDescricaoArtefato(objeto.getArtefato().getDescricaoArtefato());
        this.setAtivo(objeto.getArtefato().getAtivo());
        this.setTipoArtefato(objeto.getArtefato().getTipoArtefato());
        this.setDataCadastro(objeto.getArtefato().getDataCadastro().toString());

        if (objeto.getArtefato() != null && objeto.getArtefato().getDataAtualizacao() != null) {
            this.setDataAtualizacao(objeto.getArtefato().getDataAtualizacao().toString());
        } else {
            this.setDataAtualizacao("");
        }

        this.setIdUsuario(objeto.getArtefato().getIdUsuario());
        this.setTelefoneUsuario(objeto.getArtefato().getUsuario().getTelefoneUsuario());
        this.setListaImagens(objeto.getArtefato().getListaImagens());
    }
}
