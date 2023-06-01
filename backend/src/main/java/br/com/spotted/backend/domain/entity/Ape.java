package br.com.spotted.backend.domain.entity;


import br.com.spotted.backend.domain.entity.image.ApeImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name= "id_usuario")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name="id_Usuario", referencedColumnName = "id_usuario", updatable = false, insertable = false) //Fk IdUsuario na tabela Ape
    private Usuario usuario;

    @OneToMany(mappedBy = "idApe", targetEntity = ApeImage.class, orphanRemoval = true) //idApe relacionamento na outra table
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<ApeImage> listaImagensApe = new ArrayList<ApeImage>();;
}