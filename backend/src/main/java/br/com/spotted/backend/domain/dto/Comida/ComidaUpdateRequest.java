package br.com.spotted.backend.domain.dto.Comida;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ComidaUpdateRequest {

    @NotEmpty(message = "Campo nome é obrigatório!")
    private String tituloComida;

    @NotEmpty(message = "Campo 'descrição' é obrigatório!")
    private String descricaoComida;

    @NotEmpty(message = "Campo 'tipo' é obrigatório!")
    private String tipoComida;

    @NotEmpty(message = "Campo 'marca' é obrigatório!")
    private String marcaComida;

    @NotNull(message = "Campo 'preço' é obrigatório!")
    private Double preco_comida;

    @NotEmpty(message = "Campo 'oferta' é obrigatório!")
    private String ofertaComida;

    private byte[] imagemComida;
}
