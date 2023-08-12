package br.com.spotted.backend.domain.dto.Moradia;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoCreateRequest;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MoradiaCreateRequest {

    @NotEmpty
    private String estadoMoradia;

    @NotEmpty
    private String cidadeMoradia;

    private String bairroMoradia;

    private String cepMoradia;

    private Integer qtdMoradoresPermitidoMoradia;

    private Integer qtdMoradoresAtuaisMoradia;

    private Double precoAluguelTotalMoradia;

    private Double precoAluguelPorPessoaMoradia;

    private String vagaGaragemMoradia;


    private String animaisEstimacaoMoradia;

    @NotNull
    private ArtefatoCreateRequest artefato;
}
