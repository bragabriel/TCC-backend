package br.com.spotted.backend.domain.dto.Comida;

import br.com.spotted.backend.domain.entity.Comida;
import br.com.spotted.backend.domain.entity.image.ComidaImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private List<ComidaImage> listaImagensComida;

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
        this.listaImagensComida = comida.getListaImagensComida();
        this.idUsuario = comida.getIdUsuario();
    }
}
