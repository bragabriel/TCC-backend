package br.com.spotted.backend.domain.dto.Emprego;

import br.com.spotted.backend.domain.entity.Emprego;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpregoResponse {

    private Long idArtefato;

    private String localizacaoEstagio;

    private String requisitosEstagio;

    private Integer salarioEstagio;

    private String beneficiosEstagio;

    private String tituloArtefato;

    private String descricaoArtefato;

    public EmpregoResponse(Emprego emprego) {
        this.idArtefato = emprego.getIdArtefato();
        this.tituloArtefato = emprego.getArtefato().getTituloArtefato();
        this.descricaoArtefato = emprego.getArtefato().getDescricaoArtefato();
        this.localizacaoEstagio = emprego.getLocalizacaoEmprego();
        this.requisitosEstagio = emprego.getRequisitosEmprego();
        this.salarioEstagio = emprego.getSalarioEmprego();
        this.beneficiosEstagio = emprego.getBeneficiosEmprego();
    }
}