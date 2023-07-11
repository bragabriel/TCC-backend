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

    private String localizacaoEmprego;

    private String requisitosEmprego;

    private Integer salarioEmprego;

    private String beneficiosEmprego;

    private String contatoEmprego;

    private String linkVagaEmprego;

    private String tituloArtefato;

    private String descricaoArtefato;

    public EmpregoResponse(Emprego emprego) {
        this.idArtefato = emprego.getIdArtefato();
        this.tituloArtefato = emprego.getArtefato().getTituloArtefato();
        this.descricaoArtefato = emprego.getArtefato().getDescricaoArtefato();
        this.localizacaoEmprego = emprego.getLocalizacaoEmprego();
        this.requisitosEmprego = emprego.getRequisitosEmprego();
        this.salarioEmprego = emprego.getSalarioEmprego();
        this.beneficiosEmprego = emprego.getBeneficiosEmprego();
        this.contatoEmprego = emprego.getContatoEmprego();
        this.linkVagaEmprego = emprego.getLinkVagaEmprego();
    }
}