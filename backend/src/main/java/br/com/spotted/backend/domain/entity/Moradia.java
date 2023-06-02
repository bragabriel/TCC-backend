package br.com.spotted.backend.domain.entity;


import br.com.spotted.backend.domain.entity.image.MoradiaImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="Moradia")
@AllArgsConstructor
@NoArgsConstructor
public class Moradia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moradia")
    private Long idMoradia;

    @Column(name = "titulo_moradia", nullable = false)
    private String tituloMoradia;

    @Column(name = "descricao_moradia", nullable = false)
    private String descricaoMoradia;

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

    @Column(name= "id_usuario")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name="id_Usuario", referencedColumnName = "id_usuario", updatable = false, insertable = false) //Fk IdUsuario na tabela Moradia
    private Usuario usuario;

    @OneToMany(mappedBy = "idMoradia", targetEntity = MoradiaImage.class, orphanRemoval = true) //idMoradia relacionamento na outra table
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<MoradiaImage> listaImagensMoradia = new ArrayList<MoradiaImage>();;
}