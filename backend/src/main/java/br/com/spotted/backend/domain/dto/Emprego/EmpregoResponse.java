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

    private String tituloEstagio;

    private String descricaoEstagio;

    private String localizacaoEstagio;

    private String requisitosEstagio;

    private Integer salarioEstagio;

    private String beneficiosEstagio;

    private String artefato_titulo;

    private String artefato_descricao;


    public EmpregoResponse(Emprego emprego) {
        this.idArtefato = emprego.getIdArtefato();
        this.artefato_titulo = emprego.getArtefato().getTituloArtefato();
        this.artefato_descricao = emprego.getArtefato().getDescricaoArtefato();
        this.localizacaoEstagio = emprego.getLocalizacaoEmprego();
        this.requisitosEstagio = emprego.getRequisitosEmprego();
        this.salarioEstagio = emprego.getSalarioEmprego();
        this.beneficiosEstagio = emprego.getBeneficiosEmprego();
    }
}