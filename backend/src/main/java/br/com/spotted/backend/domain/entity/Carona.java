package br.com.spotted.backend.domain.entity;


import br.com.spotted.backend.domain.entity.image.CaronaImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="Carona")
@AllArgsConstructor
@NoArgsConstructor
public class Carona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carona")
    private Long idCarona;

    @Column(name = "titulo_carona", nullable = false)
    private String tituloCarona;

    @Column(name = "descricao_carona", nullable = false)
    private String descricaoCarona;

    @Column(name = "informacoesVeiculo_carona", nullable = false)
    private String informacoesVeiculoCarona;

    @Column(name = "informacoesCondutor_carona", nullable = false)
    private String informacoesCondutorCarona;

    @Column(name = "qtdAssentosTotal_carona")
    private Integer qtdAssentosTotalCarona;

    @Column(name = "qtdAssentosAtual_carona")
    private Integer qtdAssentosAtualCarona;

    @Column(name = "cidade_carona")
    private String cidadeCarona;

    @Column(name = "periodo_carona")
    private String periodoCarona;

    @Column(name= "id_usuario")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario", updatable = false, insertable = false) //Fk IdUsuario na tabela Carona
    private Usuario usuario;

    @OneToMany(mappedBy = "idCarona", targetEntity = CaronaImage.class, orphanRemoval = true) //idApe relacionamento na outra table
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<CaronaImage> listaImagensCarona = new ArrayList<CaronaImage>();}
