package br.com.spotted.backend.domain.dto.Moradia;


import br.com.spotted.backend.domain.dto.Artefato.ArtefatoIndividualResponse;
import br.com.spotted.backend.domain.entity.Moradia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MoradiaResponse extends ArtefatoIndividualResponse {

    private Long idArtefato;

    private String estadoMoradia;

    private String cidadeMoradia;

    private String bairroMoradia;

    private String cepMoradia;

    private Integer qtdMoradoresPermitidoMoradia;

    private Integer qtdMoradoresAtuaisMoradia;

    private Double precoAluguelTotalMoradia;

    private Double precoAluguelPorPessoaMoradia;

    private String vagaGaragemMoradia;

    private String animaisEstimacaoMoradia;


    public MoradiaResponse(Moradia moradia) {
        super();
        this.idArtefato = moradia.getIdArtefato();
        this.estadoMoradia = moradia.getEstadoMoradia();
        this.cidadeMoradia = moradia.getCidadeMoradia();
        this.bairroMoradia = moradia.getBairroMoradia();
        this.cepMoradia = moradia.getCepMoradia();
        this.qtdMoradoresPermitidoMoradia = moradia.getQtdMoradoresPermitidoMoradia();
        this.qtdMoradoresAtuaisMoradia = moradia.getQtdMoradoresAtuaisMoradia();
        this.precoAluguelTotalMoradia = moradia.getPrecoAluguelTotalMoradia();
        this.precoAluguelPorPessoaMoradia = moradia.getPrecoAluguelPorPessoaMoradia();
        this.vagaGaragemMoradia = moradia.getVagaGaragemMoradia();
        this.animaisEstimacaoMoradia = moradia.getAnimaisEstimacaoMoradia();

        this.setTituloArtefato(moradia.getArtefato().getTituloArtefato());
        this.setDescricaoArtefato(moradia.getArtefato().getDescricaoArtefato());
        this.setAtivo(moradia.getArtefato().getAtivo());
        this.setDataCadastro(moradia.getArtefato().getDataCadastro().toString());

        if (moradia.getArtefato() != null && moradia.getArtefato().getDataAtualizacao() != null) {
            this.setDataAtualizacao(moradia.getArtefato().getDataAtualizacao().toString());
        } else {
            this.setDataAtualizacao("");
        }

        this.setIdUsuario(moradia.getArtefato().getIdUsuario());
        this.setTelefoneUsuario(moradia.getArtefato().getUsuario().getTelefoneUsuario());
        this.setListaImagens(moradia.getArtefato().getListaImagens());
    }
}