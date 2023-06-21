package br.com.spotted.backend.domain.dto.Alimento;

import br.com.spotted.backend.domain.entity.Alimento;
import br.com.spotted.backend.domain.entity.image.AlimentoImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlimentoResponse {

    private Long idAlimento;

    private String tituloAlimento;

    private String descricaoAlimento;

    private String tipoAlimento;

    private String marcaAlimento;

    private Double precoAlimento;

    private String ofertaAlimento;

    private List<AlimentoImage> listaImagensAlimento;

    private Long idUsuario;

    // Usado para mapear criar um DTO usando uma entidade
    public AlimentoResponse(Alimento alimento) {
        this.idAlimento = alimento.getIdAlimento();
        this.tituloAlimento = alimento.getTituloPai();
        this.descricaoAlimento = alimento.getDescricaoPai();
        this.precoAlimento = alimento.getPrecoAlimento();
        this.tipoAlimento = alimento.getTipoAlimento();
        this.marcaAlimento = alimento.getMarcaAlimento();
        this.ofertaAlimento = alimento.getOfertaAlimento();
        this.listaImagensAlimento = alimento.getListaImagensAlimento();
        this.idUsuario = alimento.getIdUsuario();
    }
}
