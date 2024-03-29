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
    }
}