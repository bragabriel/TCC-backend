package br.com.spotted.backend.domain.dto.Festa;

import br.com.spotted.backend.domain.entity.Festa;
import br.com.spotted.backend.domain.entity.image.ApeImage;
import br.com.spotted.backend.domain.entity.image.FestaImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FestaResponse {


    private Long idFesta;

    private String tituloFesta;

    private String descricaoFesta;

    private String localizacaoFesta;

    private List<FestaImage> listaImagensFesta;

    private Long idUsuario;

    public FestaResponse(Festa festa) {
        this.idFesta = festa.getIdFesta();
        this.tituloFesta = festa.getTituloFesta();
        this.descricaoFesta = festa.getDescricaoFesta();
        this.localizacaoFesta = festa.getLocalizacaoFesta();
        this.listaImagensFesta = festa.getListaImagensFesta();
        this.idUsuario = festa.getIdUsuario();
    }
}
