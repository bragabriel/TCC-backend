package br.com.spotted.backend.domain.dto.Ape;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ApeCreateRequest {

    @NotEmpty(message = "O campo título é obrigatório!")
    private String tituloApe;

    @NotEmpty(message = "O campo descrição é obrigatório!")
    private String descricaoApe;

    @NotEmpty(message = "O campo localização é obrigatório!")
    private String localizacaoApe;

    @NotNull(message = "O campo quantidade de moradores permitidos é obrigatório!")
    private Integer qtdMoradoresPermitidoApe;

    @NotNull(message = "O campo quantidade de moradores atuais é obrigatório!")
    private Integer qtdMoradoresAtuaisApe;

    @NotEmpty(message = "O campo preço total do aluguel é obrigatório!")
    private Double precoAluguelTotalApe;

    @NotEmpty(message = "O campo preço do aluguel por pessoa é obrigatório!")
    private Double precoAluguelPorPessoaApe;

    @NotEmpty(message = "O campo vaga garagem é obrigatório!")
    private String vagaGaragemApe;

    @NotEmpty(message = "O campo animais de estimação é obrigatório!")
    private String animaisEstimacaoApe;

    private byte[] imagemComida;

    @NotNull
    private Long idUsuario;
}
