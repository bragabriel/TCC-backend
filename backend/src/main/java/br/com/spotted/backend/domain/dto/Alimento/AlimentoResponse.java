package br.com.spotted.backend.domain.dto.Alimento;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoIndividualResponse;
import br.com.spotted.backend.domain.entity.Alimento;
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
        this.setAtivo(alimento.getArtefato().getAtivo());
        this.setTipoArtefato(alimento.getArtefato().getTipoArtefato());
        this.setDataCadastro(alimento.getArtefato().getDataCadastro().toString());

        if (alimento.getArtefato() != null && alimento.getArtefato().getDataAtualizacao() != null) {
            this.setDataAtualizacao(alimento.getArtefato().getDataAtualizacao().toString());
        } else {
            this.setDataAtualizacao("");
        }

        this.setIdUsuario(alimento.getArtefato().getIdUsuario());
        this.setTelefoneUsuario(alimento.getArtefato().getUsuario().getTelefoneUsuario());
        this.setListaImagens(alimento.getArtefato().getListaImagens());
    }
}