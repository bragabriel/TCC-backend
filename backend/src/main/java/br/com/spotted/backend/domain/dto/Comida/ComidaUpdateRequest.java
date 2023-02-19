package br.com.spotted.backend.domain.dto.Comida;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

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

    @NotEmpty(message = "Campo 'oferta' é obrigatório!")
    private String ofertaComida;

    private byte[] imagemComida;

}
