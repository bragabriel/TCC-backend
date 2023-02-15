package br.com.spotted.backend.domain.dto.Comida;

import br.com.spotted.backend.domain.entity.Comida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComidaResponse {

    private Long idComida;

    private String nomeComida;

    private String tipoComida;

    private byte[] imagemComida;

    private Long idUsuario;


    // Usado para mapear criar um DTO usando uma entidade
    public ComidaResponse(Comida comida) {
        this.idComida = comida.getIdComida();
        this.nomeComida = comida.getTituloComida();
        this.tipoComida = comida.getTipoComida();
        this.imagemComida = comida.getImagemComida();
        this.idUsuario = comida.getIdUsuario();
    }
}
