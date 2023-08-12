package br.com.spotted.backend.domain.dto.Alimento;

import lombok.Data;

@Data
public class AlimentoUpdateRequest {

    private String tituloArtefato;

    private String descricaoArtefato;

    private TipoAlimento tipoAlimento;

    private String marcaAlimento;

    private String saborAlimento;

    private String unidadeAlimento;

    private Double precoAlimento;

    private String ofertaAlimento;
}