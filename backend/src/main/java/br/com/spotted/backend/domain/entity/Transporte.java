package br.com.spotted.backend.domain.entity;


import br.com.spotted.backend.domain.entity.image.TransporteImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="Transporte")
@AllArgsConstructor
@NoArgsConstructor
public class Transporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transporte")
    private Long idTransporte;

    @Column(name = "titulo_transporte", nullable = false)
    private String tituloTransporte;

    @Column(name = "descricao_transporte", nullable = false)
    private String descricaoTransporte;

    @Column(name = "informacoesVeiculo_transporte", nullable = false)
    private String informacoesVeiculoTransporte;

    @Column(name = "informacoesCondutor_transporte", nullable = false)
    private String informacoesCondutorTransporte;

    @Column(name = "qtdAssentosTotal_transporte")
    private Integer qtdAssentosTotalTransporte;

    @Column(name = "qtdAssentosPreenchidos_transporte")
    private Integer qtdAssentosPreenchidosTransporte;

    @Column(name = "cidade_transporte")
    private String cidadeTransporte;

    @Column(name = "periodo_transporte")
    private String periodoTransporte;

    @Column(name= "id_usuario")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario", updatable = false, insertable = false) //Fk IdUsuario na tabela Transporte
    private Usuario usuario;

    @OneToMany(mappedBy = "idTransporte", targetEntity = TransporteImage.class, orphanRemoval = true) //idApe relacionamento na outra table
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<TransporteImage> listaImagensTransporte = new ArrayList<TransporteImage>();}
