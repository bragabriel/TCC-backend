package br.com.spotted.backend.domain.dto.Transporte;

import br.com.spotted.backend.domain.entity.Transporte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransporteResponse{

    private Long idArtefato;

    private String informacoesVeiculoTransporte;

    private String informacoesCondutorTransporte;

    private Integer qtdAssentosTotalTransporte;

    private Integer qtdAssentosPreenchidosTransporte;

    private String cidadeTransporte;

    private String periodoTransporte;

    private String valorTransporte;

    public TransporteResponse(Transporte transporte) {
        this.idArtefato = transporte.getIdArtefato();
        this.informacoesVeiculoTransporte = transporte.getInformacoesVeiculoTransporte();
        this.informacoesCondutorTransporte = transporte.getInformacoesCondutorTransporte();
        this.qtdAssentosTotalTransporte = transporte.getQtdAssentosTotalTransporte();
        this.qtdAssentosPreenchidosTransporte = transporte.getQtdAssentosPreenchidosTransporte();
        this.cidadeTransporte = transporte.getCidadeTransporte();
        this.periodoTransporte = transporte.getPeriodoTransporte();
        this.valorTransporte = transporte.getValorTransporte();
    }
}
