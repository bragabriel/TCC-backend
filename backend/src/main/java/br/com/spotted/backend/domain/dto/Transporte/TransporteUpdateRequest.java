package br.com.spotted.backend.domain.dto.Transporte;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
public class TransporteUpdateRequest {

    @NotEmpty(message = "Campo nome é obrigatório!")
    private String tituloArtefato;

    @NotEmpty(message = "Campo 'descrição' é obrigatório!")
    private String descricaoArtefato;

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
}
