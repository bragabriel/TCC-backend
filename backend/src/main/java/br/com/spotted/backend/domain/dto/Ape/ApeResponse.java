package br.com.spotted.backend.domain.dto.Ape;


import br.com.spotted.backend.domain.entity.Ape;
import br.com.spotted.backend.domain.entity.image.ApeImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApeResponse {

    private Long idApe;

    private String tituloApe;

    private String descricaoApe;

    private String localizacaoApe;

    private Integer qtdMoradoresPermitidoApe;

    private Integer qtdMoradoresAtuaisApe;

    private Double precoAluguelTotalApe;

    private Double precoAluguelPorPessoaApe;

    private String vagaGaragemApe;

    private String animaisEstimacaoApe;

    private List<ApeImage> listaImagensApe;

    private Long idUsuario;

    // Usado para mapear criar um DTO usando uma entidade
    public ApeResponse(Ape ape) {
        this.idApe = ape.getIdApe();
        this.tituloApe = ape.getTituloApe();
        this.descricaoApe = ape.getDescricaoApe();
        this.localizacaoApe = ape.getLocalizacaoApe();
        this.qtdMoradoresPermitidoApe = ape.getQtdMoradoresPermitidoApe();
        this.qtdMoradoresAtuaisApe = ape.getQtdMoradoresAtuaisApe();
        this.precoAluguelTotalApe = ape.getPrecoAluguelTotalApe();
        this.precoAluguelPorPessoaApe = ape.getPrecoAluguelPorPessoaApe();
        this.vagaGaragemApe = ape.getVagaGaragemApe();
        this.animaisEstimacaoApe = ape.getAnimaisEstimacaoApe();
        this.listaImagensApe = ape.getListaImagensApe();
        this.idUsuario = ape.getIdUsuario();
    }
}