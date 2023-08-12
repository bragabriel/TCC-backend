package br.com.spotted.backend.domain.dto.Moradia;

import lombok.Data;


@Data
public class MoradiaUpdateRequest {

    private String tituloArtefato;

    private String descricaoArtefato;

    private String estadoMoradia;

    private String cidadeMoradia;

    private String bairroMoradia;

    private String cepMoradia;

    private Integer qtdMoradoresPermitidoMoradia;

    private Integer qtdMoradoresAtuaisMoradia;

    private Double precoAluguelTotalMoradia;

    private Double precoAluguelPorPessoaMoradia;

    private String vagaGaragemMoradia;

    private String animaisEstimacaoMoradia;
}
