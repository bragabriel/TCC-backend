package br.com.spotted.backend.domain.dto.Festa;

import br.com.spotted.backend.domain.entity.Festa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class FestaResponse {

    private Long idArtefato;

    private String tituloFesta;

    private String descricaoFesta;

    private String localizacaoFesta;

    private String artefato_titulo;

    private String artefato_descricao;

    public FestaResponse(Festa festa) {
        this.idArtefato = festa.getIdArtefato();
        this.localizacaoFesta = festa.getLocalizacaoFesta();
        this.artefato_titulo = festa.getArtefato().getTituloArtefato();
        this.artefato_descricao = festa.getArtefato().getDescricaoArtefato();
    }
}
