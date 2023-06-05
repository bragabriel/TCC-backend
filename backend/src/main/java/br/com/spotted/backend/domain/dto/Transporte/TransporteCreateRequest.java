package br.com.spotted.backend.domain.dto.Transporte;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TransporteCreateRequest {

    @NotEmpty
    private String tituloTransporte;

    @NotEmpty
    private String descricaoTransporte;

    @NotEmpty
    private String informacoesVeiculoTransporte;

    @NotEmpty
    private String informacoesCondutorTransporte;

    @NotNull
    private Integer qtdAssentosTotalTransporte;

    @NotNull
    private Integer qtdAssentosPreenchidosTransporte;

    @NotEmpty
    private String cidadeTransporte;

    @NotEmpty
    private String periodoTransporte;

    @NotNull
    private Long idUsuario;
}
