package br.com.spotted.backend.domain.dto.Carona;

import br.com.spotted.backend.domain.entity.Carona;
import br.com.spotted.backend.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private byte[] imagemCarona;

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
        this.imagemCarona = carona.getImagemCarona();
        this.idUsuario = carona.getIdUsuario();
    }
}
