package br.com.spotted.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="Moradia")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Moradia {

    @Id
    @Column(name = "id_artefato")
    private Long idArtefato;

    @Column(name = "localizacao_moradia")
    private String localizacaoMoradia;

    @Column(name = "qtdMoradoresPermitido_moradia")
    private Integer qtdMoradoresPermitidoMoradia;

    @Column(name = "qtdMoradoresAtuais_moradia")
    private Integer qtdMoradoresAtuaisMoradia;

    @Column(name = "precoAluguelTotal_moradia")
    private Double precoAluguelTotalMoradia;

    @Column(name = "precoAluguelPorPessoa_moradia")
    private Double precoAluguelPorPessoaMoradia;

    @Column(name = "vagaGaragem_moradia")
    private String vagaGaragemMoradia;

    @Column(name = "animaisEstimacao_moradia")
    private String animaisEstimacaoMoradia;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //Uma moradia pode ter 1-1 artefato
    @JoinColumn(name = "id_artefato")
    private Artefato artefato;
}