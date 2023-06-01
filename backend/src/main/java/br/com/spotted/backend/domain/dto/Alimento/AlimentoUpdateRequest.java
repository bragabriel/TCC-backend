package br.com.spotted.backend.domain.dto.Alimento;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AlimentoUpdateRequest {

    @NotEmpty(message = "Campo nome é obrigatório!")
    private String tituloAlimento;

    @NotEmpty(message = "Campo 'descrição' é obrigatório!")
    private String descricaoAlimento;

    @NotEmpty(message = "Campo 'tipo' é obrigatório!")
    private String tipoAlimento;

    @NotEmpty(message = "Campo 'marca' é obrigatório!")
    private String marcaAlimento;

    @NotNull(message = "Campo 'preço' é obrigatório!")
    private Double precoAlimento;

    @NotEmpty(message = "Campo 'oferta' é obrigatório!")
    private String ofertaAlimento;
}