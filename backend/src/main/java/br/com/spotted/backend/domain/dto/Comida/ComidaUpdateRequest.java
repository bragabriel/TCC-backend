package br.com.spotted.backend.domain.dto.Comida;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Data
public class ComidaUpdateRequest {

    @NotEmpty(message = "Nome é obrigatório!")
    private String nomeComida;

    @NotEmpty(message = "Tipo é obrigatório!")
    private String tipoComida;

    private byte[] imagemComida;

}
