package br.com.spotted.backend.domain.dto.Moradia;


import br.com.spotted.backend.domain.entity.Moradia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MoradiaResponse {

    private Long idArtefato;

    private String localizacaoMoradia;

    private Integer qtdMoradoresPermitidoMoradia;

    private Integer qtdMoradoresAtuaisMoradia;

    private Double precoAluguelTotalMoradia;

    private Double precoAluguelPorPessoaMoradia;

    private String vagaGaragemMoradia;

    private String animaisEstimacaoMoradia;

    private String artefato_titulo;

    private String artefato_descricao;

    public MoradiaResponse(Moradia moradia) {
        this.idArtefato = moradia.getIdArtefato();
        this.localizacaoMoradia = moradia.getLocalizacaoMoradia();
        this.qtdMoradoresPermitidoMoradia = moradia.getQtdMoradoresPermitidoMoradia();
        this.qtdMoradoresAtuaisMoradia = moradia.getQtdMoradoresAtuaisMoradia();
        this.precoAluguelTotalMoradia = moradia.getPrecoAluguelTotalMoradia();
        this.precoAluguelPorPessoaMoradia = moradia.getPrecoAluguelPorPessoaMoradia();
        this.vagaGaragemMoradia = moradia.getVagaGaragemMoradia();
        this.animaisEstimacaoMoradia = moradia.getAnimaisEstimacaoMoradia();
        this.artefato_titulo = moradia.getArtefato().getTituloArtefato();
        this.artefato_descricao = moradia.getArtefato().getDescricaoArtefato();
    }
}