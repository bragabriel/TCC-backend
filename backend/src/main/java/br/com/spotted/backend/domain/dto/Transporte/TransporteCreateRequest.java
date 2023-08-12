package br.com.spotted.backend.domain.dto.Transporte;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoCreateRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransporteCreateRequest {

    private String informacoesVeiculoTransporte;

    private String informacoesCondutorTransporte;

    private Integer qtdAssentosTotalTransporte;

    private Integer qtdAssentosPreenchidosTransporte;

    private String cidadeTransporte;

    private String periodoTransporte;

    private String valorTransporte;

    @NotNull
    private ArtefatoCreateRequest artefato;
}
