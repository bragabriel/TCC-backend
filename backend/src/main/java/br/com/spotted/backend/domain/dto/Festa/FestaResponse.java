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

    private String localizacaoFesta;

    public FestaResponse(Festa festa) {
        this.idArtefato = festa.getIdArtefato();
        this.localizacaoFesta = festa.getLocalizacaoFesta();
    }
}
