package br.com.spotted.backend.domain.dto.Moradia;


import br.com.spotted.backend.domain.entity.Moradia;
import br.com.spotted.backend.domain.entity.image.MoradiaImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MoradiaResponse {

    private Long idMoradia;

    private String tituloMoradia;

    private String descricaoMoradia;

    private String localizacaoMoradia;

    private Integer qtdMoradoresPermitidoMoradia;

    private Integer qtdMoradoresAtuaisMoradia;

    private Double precoAluguelTotalMoradia;

    private Double precoAluguelPorPessoaMoradia;

    private String vagaGaragemMoradia;

    private String animaisEstimacaoMoradia;

    private List<MoradiaImage> listaImagensMoradia;

    private Long idUsuario;

    // Usado para mapear criar um DTO usando uma entidade
    public MoradiaResponse(Moradia moradia) {
        this.idMoradia = moradia.getIdMoradia();
        this.tituloMoradia = moradia.getTituloMoradia();
        this.descricaoMoradia = moradia.getDescricaoMoradia();
        this.localizacaoMoradia = moradia.getLocalizacaoMoradia();
        this.qtdMoradoresPermitidoMoradia = moradia.getQtdMoradoresPermitidoMoradia();
        this.qtdMoradoresAtuaisMoradia = moradia.getQtdMoradoresAtuaisMoradia();
        this.precoAluguelTotalMoradia = moradia.getPrecoAluguelTotalMoradia();
        this.precoAluguelPorPessoaMoradia = moradia.getPrecoAluguelPorPessoaMoradia();
        this.vagaGaragemMoradia = moradia.getVagaGaragemMoradia();
        this.animaisEstimacaoMoradia = moradia.getAnimaisEstimacaoMoradia();
        this.listaImagensMoradia = moradia.getListaImagensMoradia();
        this.idUsuario = moradia.getIdUsuario();
    }
}