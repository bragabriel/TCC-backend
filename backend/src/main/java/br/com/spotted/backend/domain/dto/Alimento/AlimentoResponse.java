package br.com.spotted.backend.domain.dto.Alimento;

import br.com.spotted.backend.domain.entity.Alimento;
import br.com.spotted.backend.domain.entity.Artefato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

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

    private String artefato;

    //private List<AlimentoImage> listaImagensAlimento;


    // Usado para mapear criar um DTO usando uma entidade
    public AlimentoResponse(Alimento alimento) {
        this.idArtefato = alimento.getIdArtefato();
        this.tipoAlimento = alimento.getTipoAlimento();
        this.marcaAlimento = alimento.getMarcaAlimento();
        this.saborAlimento = alimento.getSaborAlimento();
        this.unidadeAlimento = alimento.getUnidadeAlimento();
        this.precoAlimento = alimento.getPrecoAlimento();
        this.ofertaAlimento = alimento.getOfertaAlimento();
        this.artefato = alimento.getArtefato().getDescricaoArtefato();
    }
        //this.artefato = alimento.getArtefato();
        //this.listaImagensAlimento = alimento.getListaImagensAlimento();
}