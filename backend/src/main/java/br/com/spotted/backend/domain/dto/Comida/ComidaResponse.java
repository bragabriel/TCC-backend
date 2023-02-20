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

    private String tituloComida;

    private String descricaoComida;

    private String tipoComida;

    private String marcaComida;

    private Double precoComida;
    private String ofertaComida;

    private byte[] imagemComida = null;

    private Long idUsuario;

    // Usado para mapear criar um DTO usando uma entidade
    public ComidaResponse(Comida comida) {
        this.idComida = comida.getIdComida();
        this.tituloComida = comida.getTituloComida();
        this.descricaoComida = comida.getTipoComida();
        this.precoComida = comida.getPrecoComida();
        this.tipoComida = comida.getDescricaoComida();
        this.marcaComida = comida.getTipoComida();
        this.ofertaComida = comida.getTipoComida();
        this.imagemComida = comida.getImagemComida();
        this.idUsuario = comida.getIdUsuario();
    }
}
