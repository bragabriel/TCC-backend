package br.com.spotted.backend.domain.dto.Transporte;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoIndividualResponse;
import br.com.spotted.backend.domain.entity.Transporte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransporteArtefatoResponse extends ArtefatoIndividualResponse {

    private Long idArtefato;

    private String informacoesVeiculoTransporte;

    private String informacoesCondutorTransporte;

    private Integer qtdAssentosTotalTransporte;

    private Integer qtdAssentosPreenchidosTransporte;

    private String cidadeTransporte;

    private String periodoTransporte;

    private String valorTransporte;

    public TransporteArtefatoResponse(Transporte transporte) {
        super();
        this.idArtefato = transporte.getIdArtefato();
        this.informacoesVeiculoTransporte = transporte.getInformacoesVeiculoTransporte();
        this.informacoesCondutorTransporte = transporte.getInformacoesCondutorTransporte();
        this.qtdAssentosTotalTransporte = transporte.getQtdAssentosTotalTransporte();
        this.qtdAssentosPreenchidosTransporte = transporte.getQtdAssentosPreenchidosTransporte();
        this.cidadeTransporte = transporte.getCidadeTransporte();
        this.periodoTransporte = transporte.getPeriodoTransporte();
        this.valorTransporte = transporte.getValorTransporte();

        this.setTituloArtefato(transporte.getArtefato().getTituloArtefato());
        this.setDescricaoArtefato(transporte.getArtefato().getDescricaoArtefato());
        this.setAtivo(transporte.getArtefato().getAtivo());
        this.setTipoArtefato(transporte.getArtefato().getTipoArtefato());
        this.setDataCadastro(transporte.getArtefato().getDataCadastro().toString());

        if (transporte.getArtefato() != null && transporte.getArtefato().getDataAtualizacao() != null) {
            this.setDataAtualizacao(transporte.getArtefato().getDataAtualizacao().toString());
        } else {
            this.setDataAtualizacao("");
        }

        this.setIdUsuario(transporte.getArtefato().getIdUsuario());
        this.setTelefoneUsuario(transporte.getArtefato().getUsuario().getTelefoneUsuario());
        this.setListaImagens(transporte.getArtefato().getListaImagens());
    }
}
