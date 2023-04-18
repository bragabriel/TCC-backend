package br.com.spotted.backend.domain.dto.Estagio;

import br.com.spotted.backend.domain.entity.Estagio;
import br.com.spotted.backend.domain.entity.image.EstagioImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstagioResponse {

    private Long idEstagio;

    private String tituloEstagio;

    private String descricaoEstagio;

    private String localizacaoEstagio;

    private String requisitosEstagio;

    private Integer salarioEstagio;

    private String beneficiosEstagio;

    private List<EstagioImage> listaImagensEstagio;

    private Long idUsuario;

    public EstagioResponse(Estagio estagio) {
        this.idEstagio = estagio.getIdEstagio();
        this.tituloEstagio = estagio.getTituloEstagio();
        this.descricaoEstagio = estagio.getDescricaoEstagio();
        this.localizacaoEstagio = estagio.getLocalizacaoEstagio();
        this.requisitosEstagio = estagio.getRequisitosEstagio();
        this.salarioEstagio = estagio.getSalarioEstagio();
        this.beneficiosEstagio = estagio.getBeneficiosEstagio();
        this.listaImagensEstagio = estagio.getListaImagensEstagio();
        this.idUsuario = estagio.getIdUsuario();
    }
}
