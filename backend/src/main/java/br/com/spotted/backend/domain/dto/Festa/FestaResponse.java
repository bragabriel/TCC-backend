package br.com.spotted.backend.domain.dto.Festa;

import br.com.spotted.backend.domain.entity.Festa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FestaResponse {


    private Long idFesta;

    private String tituloFesta;

    private String descricaoFesta;

    private String localizacaoFesta;

    private byte[] imagemFesta;

    private Long idUsuario;

    public FestaResponse(Festa festa) {
        this.idFesta = festa.getIdFesta();
        this.tituloFesta = festa.getTituloFesta();
        this.descricaoFesta = festa.getDescricaoFesta();
        this.localizacaoFesta = festa.getLocalizacaoFesta();
        this.imagemFesta = festa.getImagemFesta();
        this.idUsuario = festa.getIdUsuario();
    }
}
