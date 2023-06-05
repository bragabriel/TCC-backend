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

    private Long idTransporte;

    private String tituloTransporte;

    private String descricaoTransporte;

    private String informacoesVeiculoTransporte;

    private String informacoesCondutorTransporte;

    private Integer qtdAssentosTotalTransporte;

    private Integer qtdAssentosPreenchidosTransporte;

    private String cidadeTransporte;

    private String periodoTransporte;

    private List<TransporteImage> listaImagensTransporte;

    private Long idUsuario;

    public TransporteResponse(Transporte transporte) {
        this.idTransporte = transporte.getIdTransporte();
        this.tituloTransporte = transporte.getTituloTransporte();
        this.descricaoTransporte = transporte.getDescricaoTransporte();
        this.informacoesVeiculoTransporte = transporte.getInformacoesVeiculoTransporte();
        this.informacoesCondutorTransporte = transporte.getInformacoesCondutorTransporte();
        this.qtdAssentosTotalTransporte = transporte.getQtdAssentosTotalTransporte();
        this.qtdAssentosPreenchidosTransporte = transporte.getQtdAssentosPreenchidosTransporte();
        this.cidadeTransporte = transporte.getCidadeTransporte();
        this.periodoTransporte = transporte.getPeriodoTransporte();
        this.listaImagensTransporte = transporte.getListaImagensTransporte();
        this.idUsuario = transporte.getIdUsuario();
    }
}
