package br.com.spotted.backend.domain.dto.Moradia;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MoradiaUpdateRequest {

    @NotEmpty(message = "Campo nome é obrigatório!")
    private String tituloArtefato;

    @NotEmpty(message = "Campo 'descrição' é obrigatório!")
    private String descricaoArtefato;

    @NotEmpty(message = "O campo localização é obrigatório!")
    private String localizacaoMoradia;

    @NotNull(message = "O campo quantidade de moradores permitidos é obrigatório!")
    private Integer qtdMoradoresPermitidoMoradia;

    @NotNull(message = "O campo quantidade de moradores atuais é obrigatório!")
    private Integer qtdMoradoresAtuaisMoradia;

    @NotNull(message = "O campo preço total do aluguel é obrigatório!")
    private Double precoAluguelTotalMoradia;

    @NotNull(message = "O campo preço do aluguel por pessoa é obrigatório!")
    private Double precoAluguelPorPessoaMoradia;

    @NotEmpty(message = "O campo vaga garagem é obrigatório!")
    private String vagaGaragemMoradia;

    @NotEmpty(message = "O campo animais de estimação é obrigatório!")
    private String animaisEstimacaoMoradia;
}
