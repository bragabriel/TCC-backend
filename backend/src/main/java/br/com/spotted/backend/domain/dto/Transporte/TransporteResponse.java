package br.com.spotted.backend.domain.dto.Transporte;

import br.com.spotted.backend.domain.entity.Transporte;
import br.com.spotted.backend.domain.entity.image.TransporteImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransporteResponse {

    private Long idArtefato;

    private String informacoesVeiculoTransporte;

    private String informacoesCondutorTransporte;

    private Integer qtdAssentosTotalTransporte;

    private Integer qtdAssentosPreenchidosTransporte;

    private String cidadeTransporte;

    private String periodoTransporte;

    private String artefato;

    //private List<TransporteImage> listaImagensTransporte;

    public TransporteResponse(Transporte transporte) {
        this.idArtefato = transporte.getIdArtefato();
        this.informacoesVeiculoTransporte = transporte.getInformacoesVeiculoTransporte();
        this.informacoesCondutorTransporte = transporte.getInformacoesCondutorTransporte();
        this.qtdAssentosTotalTransporte = transporte.getQtdAssentosTotalTransporte();
        this.qtdAssentosPreenchidosTransporte = transporte.getQtdAssentosPreenchidosTransporte();
        this.cidadeTransporte = transporte.getCidadeTransporte();
        this.periodoTransporte = transporte.getPeriodoTransporte();
        this.artefato = transporte.getArtefato().getDescricaoArtefato();
        //this.listaImagensTransporte = transporte.getListaImagensTransporte();
    }
}
