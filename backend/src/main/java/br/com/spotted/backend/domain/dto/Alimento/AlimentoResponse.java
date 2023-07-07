package br.com.spotted.backend.domain.dto.Alimento;

import br.com.spotted.backend.domain.entity.Alimento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlimentoResponse {

    private Long idArtefato;

    private String tipoAlimento;

    private String marcaAlimento;

    private String saborAlimento;

    private String unidadeAlimento;

    private Double precoAlimento;

    private String ofertaAlimento;

    private String tituloArtefato;

    private String descricaoArtefato;

    //private List<AlimentoImage> listaImagensAlimento;

    public AlimentoResponse(Alimento alimento) {
        this.idArtefato = alimento.getIdArtefato();
        this.tipoAlimento = alimento.getTipoAlimento();
        this.marcaAlimento = alimento.getMarcaAlimento();
        this.saborAlimento = alimento.getSaborAlimento();
        this.unidadeAlimento = alimento.getUnidadeAlimento();
        this.precoAlimento = alimento.getPrecoAlimento();
        this.ofertaAlimento = alimento.getOfertaAlimento();
        this.tituloArtefato = alimento.getArtefato().getTituloArtefato();
        this.descricaoArtefato = alimento.getArtefato().getDescricaoArtefato();
    }
}