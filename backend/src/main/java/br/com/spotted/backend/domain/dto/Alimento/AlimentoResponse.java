package br.com.spotted.backend.domain.dto.Alimento;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoIndividualResponse;
import br.com.spotted.backend.domain.entity.Alimento;
import br.com.spotted.backend.domain.entity.Imagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlimentoResponse extends ArtefatoIndividualResponse {

    private Long idArtefato;

    private String tipoAlimento;

    private String marcaAlimento;

    private String saborAlimento;

    private String unidadeAlimento;

    private Double precoAlimento;

    private String ofertaAlimento;


    public AlimentoResponse(Alimento alimento) {
        super();
        this.idArtefato = alimento.getIdArtefato();
        this.tipoAlimento = alimento.getTipoAlimento();
        this.marcaAlimento = alimento.getMarcaAlimento();
        this.saborAlimento = alimento.getSaborAlimento();
        this.unidadeAlimento = alimento.getUnidadeAlimento();
        this.precoAlimento = alimento.getPrecoAlimento();
        this.ofertaAlimento = alimento.getOfertaAlimento();

        this.setTituloArtefato(alimento.getArtefato().getTituloArtefato());
        this.setDescricaoArtefato(alimento.getArtefato().getDescricaoArtefato());
        this.setTipoAlimento(alimento.getArtefato().getTipoArtefato());
        this.setAtivo(alimento.getArtefato().getAtivo());
        this.setDataCadastro(alimento.getArtefato().getDataCadastro());
        this.setDataAtualizacao(alimento.getArtefato().getDataAtualizacao());
        this.setIdUsuario(alimento.getArtefato().getIdUsuario());
        this.setListaImagens(alimento.getArtefato().getListaImagens());
    }
}