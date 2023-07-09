package br.com.spotted.backend.domain.dto.Transporte;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoCreateRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TransporteCreateRequest {

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
    private ArtefatoCreateRequest artefato;
}
