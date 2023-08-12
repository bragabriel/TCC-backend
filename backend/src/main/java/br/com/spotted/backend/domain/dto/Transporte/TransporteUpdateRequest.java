package br.com.spotted.backend.domain.dto.Transporte;

import lombok.Data;

@Data
public class TransporteUpdateRequest {

    private String tituloArtefato;

    private String descricaoArtefato;

    private String informacoesVeiculoTransporte;

    private String informacoesCondutorTransporte;

    private Integer qtdAssentosTotalTransporte;

    private Integer qtdAssentosPreenchidosTransporte;

    private String cidadeTransporte;

    private String periodoTransporte;

    private String valorTransporte;
}
