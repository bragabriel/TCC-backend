package br.com.spotted.backend.domain.dto.Carona;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
public class CaronaUpdateRequest {

    @NotEmpty
    private String tituloCarona;

    @NotEmpty
    private String descricaoCarona;

    @NotEmpty
    private String informacoesVeiculoCarona;

    @NotEmpty
    private String informacoesCondutorCarona;

    @NotNull
    private Integer qtdAssentosTotalCarona;

    @NotNull
    private Integer qtdAssentosAtualCarona;

    @NotEmpty
    private String cidadeCarona;

    @NotEmpty
    private String periodoCarona;

    private byte[] imagemCarona;
}
