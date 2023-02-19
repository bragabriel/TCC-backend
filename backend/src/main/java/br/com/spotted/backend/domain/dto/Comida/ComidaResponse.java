package br.com.spotted.backend.domain.dto.Comida;

import br.com.spotted.backend.domain.entity.Comida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComidaResponse {

    private Long idComida;

    private String tituloComida;

    private String descricaoComida;

    private String tipoComida;

    private String marcaComida;

    private String ofertaComida;

    private byte[] imagemComida = null;

    @NotNull
    private Long idUsuario;

    // Usado para mapear criar um DTO usando uma entidade

    public ComidaResponse(Comida comida) {
        this.idComida = comida.getIdComida();
        this.tituloComida = comida.getTituloComida();
        this.descricaoComida = comida.getTipoComida();
        this.tipoComida = comida.getDescricaoComida();
        this.marcaComida = comida.getTipoComida();
        this.ofertaComida = comida.getTipoComida();
        this.imagemComida = comida.getImagemComida();
        this.idUsuario = comida.getIdComida();
    }
}
