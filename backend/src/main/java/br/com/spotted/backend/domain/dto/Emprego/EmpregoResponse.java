package br.com.spotted.backend.domain.dto.Emprego;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoIndividualResponse;
import br.com.spotted.backend.domain.entity.Emprego;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpregoResponse extends ArtefatoIndividualResponse {

    private Long idArtefato;

    private String localizacaoEmprego;

    private String requisitosEmprego;

    private Integer salarioEmprego;

    private String beneficiosEmprego;

    private String contatoEmprego;

    private String linkVagaEmprego;

    private String tituloArtefato;

    private String descricaoArtefato;

    private String empresaEmprego;

    private String cidadeEmprego;

    private String estadoEmprego;

    private String experienciaEmprego;

    private String tipoVagaEmprego;

    private String presencialEmprego;


    public EmpregoResponse(Emprego emprego) {
        super();
        this.idArtefato = emprego.getIdArtefato();
        this.tituloArtefato = emprego.getArtefato().getTituloArtefato();
        this.descricaoArtefato = emprego.getArtefato().getDescricaoArtefato();
        this.localizacaoEmprego = emprego.getLocalizacaoEmprego();
        this.requisitosEmprego = emprego.getRequisitosEmprego();
        this.salarioEmprego = emprego.getSalarioEmprego();
        this.beneficiosEmprego = emprego.getBeneficiosEmprego();
        this.contatoEmprego = emprego.getContatoEmprego();
        this.linkVagaEmprego = emprego.getLinkVagaEmprego();
        this.empresaEmprego = emprego.getEmpresaEmprego();
        this.cidadeEmprego = emprego.getCidadeEmprego();
        this.estadoEmprego = emprego.getEstadoEmprego();
        this.experienciaEmprego = emprego.getExperienciaEmprego();
        this.tipoVagaEmprego = emprego.getTipoVagaEmprego();
        this.presencialEmprego = emprego.getPresencialEmprego();

        this.setTituloArtefato(emprego.getArtefato().getTituloArtefato());
        this.setDescricaoArtefato(emprego.getArtefato().getDescricaoArtefato());
        this.setTipoArtefato(emprego.getArtefato().getTipoArtefato());
        this.setAtivo(emprego.getArtefato().getAtivo());
        this.setDataCadastro(emprego.getArtefato().getDataCadastro().toString());

        if (emprego.getArtefato() != null && emprego.getArtefato().getDataAtualizacao() != null) {
            this.setDataAtualizacao(emprego.getArtefato().getDataAtualizacao().toString());
        } else {
            this.setDataAtualizacao("");
        }

        this.setIdUsuario(emprego.getArtefato().getIdUsuario());
        this.setListaImagens(emprego.getArtefato().getListaImagens());
    }
}