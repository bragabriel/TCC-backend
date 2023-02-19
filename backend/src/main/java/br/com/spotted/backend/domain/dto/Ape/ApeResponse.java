package br.com.spotted.backend.domain.dto.Ape;


import br.com.spotted.backend.domain.entity.Ape;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    private byte[] imagemComida;

    private Long idUsuario;

    // Usado para mapear criar um DTO usando uma entidade
    public ApeResponse(Ape ape) {
        this.idApe = ape.getIdApe();
        this.tituloApe = ape.getTituloApe();
        this.descricaoApe = getDescricaoApe();
        this.localizacaoApe = getLocalizacaoApe();
        this.qtdMoradoresPermitidoApe = getQtdMoradoresPermitidoApe();
        this.qtdMoradoresAtuaisApe = getQtdMoradoresAtuaisApe();
        this.precoAluguelTotalApe = getPrecoAluguelTotalApe();
        this.precoAluguelPorPessoaApe = getPrecoAluguelPorPessoaApe();
        this.vagaGaragemApe = getVagaGaragemApe();
        this.animaisEstimacaoApe = getAnimaisEstimacaoApe();
        this.imagemComida = getImagemComida();
        this.idUsuario = getIdUsuario();
    }
}