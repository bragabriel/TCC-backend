package br.com.spotted.backend.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="Ape")
@AllArgsConstructor
@NoArgsConstructor
public class Ape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ape")
    private Long idApe;

    @Column(name = "titulo_ape", nullable = false)
    private String tituloApe;

    @Column(name = "descricao_ape", nullable = false)
    private String descricaoApe;

    @Column(name = "localizacao_ape")
    private String localizacaoApe;

    @Column(name = "qtdMoradoresPermitido_ape")
    private Integer qtdMoradoresPermitidoApe;

    @Column(name = "qtdMoradoresAtuais_ape")
    private Integer qtdMoradoresAtuaisApe;

    @Column(name = "precoAluguelTotal_ape")
    private Double precoAluguelTotalApe;

    @Column(name = "precoAluguelPorPessoa_ape")
    private Double precoAluguelPorPessoaApe;

    @Column(name = "vagaGaragem_ape")
    private String vagaGaragemApe;

    @Column(name = "animaisEstimacao_ape")
    private String animaisEstimacaoApe;

    @Column(name = "imagem_ape")
    private byte[] imagemApe;

    @Column(name= "id_usuario", insertable = false, updatable = false)
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name="id_Usuario", referencedColumnName = "id_usuario", nullable = false) //Fk IdUsuario na tabela Ape
    private Usuario usuario;

}