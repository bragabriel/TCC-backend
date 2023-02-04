package br.com.spotted.backend.domain.dto.Comida;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComidaResponse {

    private Long idComida;

    private String nomeComida;

    private String tipoComida;

    private byte[] imagemUsuario;

    private Long idUsuario;
}
