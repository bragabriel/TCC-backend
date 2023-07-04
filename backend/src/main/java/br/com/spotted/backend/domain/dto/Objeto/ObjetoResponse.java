package br.com.spotted.backend.domain.dto.Objeto;

import br.com.spotted.backend.domain.entity.Objeto;
import br.com.spotted.backend.domain.entity.image.ObjetoImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ObjetoResponse {

    private Long idArtefato;

    private String localizacaoAchadoObjeto;

    private String localizacaoAtualObjeto;

    private String artefato_titulo;

    private String artefato_descricao;

    public ObjetoResponse(Objeto objeto) {
        this.idArtefato = objeto.getIdArtefato();
        this.localizacaoAchadoObjeto = objeto.getLocalizacaoAchadoObjeto();
        this.localizacaoAtualObjeto = objeto.getLocalizacaoAtualObjeto();
        this.artefato_titulo = objeto.getArtefato().getTituloArtefato();
        this.artefato_descricao = objeto.getArtefato().getDescricaoArtefato();
    }
}
