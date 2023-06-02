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

    private Long idObjeto;

    private String tituloObjeto;

    private String descricaoObjeto;

    private String localizacaoAchadoObjeto;

    private String localizacaoAtualObjeto;

    private List<ObjetoImage> listaImagensObjeto;

    private Long idUsuario;

    public ObjetoResponse(Objeto objeto) {
        this.idObjeto = objeto.getIdObjeto();
        this.tituloObjeto = objeto.getTituloObjeto();
        this.descricaoObjeto = objeto.getDescricaoObjeto();
        this.localizacaoAchadoObjeto = objeto.getLocalizacaoAchadoObjeto();
        this.localizacaoAtualObjeto = objeto.getLocalizacaoAtualObjeto();
        this.listaImagensObjeto = objeto.getListaImagensObjeto();
        this.idUsuario = objeto.getIdUsuario();
    }
}
