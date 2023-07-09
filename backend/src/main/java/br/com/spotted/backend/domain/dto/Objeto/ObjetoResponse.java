package br.com.spotted.backend.domain.dto.Objeto;

import br.com.spotted.backend.domain.entity.Objeto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ObjetoResponse {

    private Long idArtefato;

    private String localizacaoAchadoObjeto;

    private String localizacaoAtualObjeto;

    private String tituloArtefato;

    private String descricaoArtefato;

    public ObjetoResponse(Objeto objeto) {
        this.idArtefato = objeto.getIdArtefato();
        this.localizacaoAchadoObjeto = objeto.getLocalizacaoAchadoObjeto();
        this.localizacaoAtualObjeto = objeto.getLocalizacaoAtualObjeto();
        this.tituloArtefato = objeto.getArtefato().getTituloArtefato();
        this.descricaoArtefato = objeto.getArtefato().getDescricaoArtefato();
    }
}
