package br.com.spotted.backend.domain.dto.Moradia;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
