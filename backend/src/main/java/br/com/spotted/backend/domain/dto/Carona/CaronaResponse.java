package br.com.spotted.backend.domain.dto.Carona;

import br.com.spotted.backend.domain.entity.Carona;
import br.com.spotted.backend.domain.entity.image.CaronaImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CaronaResponse {

    private Long idCarona;

    private String tituloCarona;

    private String descricaoCarona;

    private String informacoesVeiculoCarona;

    private String informacoesCondutorCarona;

    private Integer qtdAssentosTotalCarona;

    private Integer qtdAssentosAtualCarona;

    private String cidadeCarona;

    private String periodoCarona;

    private List<CaronaImage> listaImagensCarona;

    private Long idUsuario;

    public CaronaResponse(Carona carona) {
        this.idCarona = carona.getIdCarona();
        this.tituloCarona = carona.getTituloCarona();
        this.descricaoCarona = carona.getDescricaoCarona();
        this.informacoesVeiculoCarona = carona.getInformacoesVeiculoCarona();
        this.informacoesCondutorCarona = carona.getInformacoesCondutorCarona();
        this.qtdAssentosTotalCarona = carona.getQtdAssentosTotalCarona();
        this.qtdAssentosAtualCarona = carona.getQtdAssentosAtualCarona();
        this.cidadeCarona = carona.getCidadeCarona();
        this.periodoCarona = carona.getPeriodoCarona();
        this.listaImagensCarona = carona.getListaImagensCarona();
        this.idUsuario = carona.getIdUsuario();
    }
}
