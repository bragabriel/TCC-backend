package br.com.spotted.backend.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String qtdAssentosTotalCarona;

    @Column(name = "qtdAssentosAtual_carona")
    private String qtdAssentosAtualCarona;

    @Column(name = "cidade_carona")
    private String cidadeCarona;

    @Column(name = "periodo_carona")
    private String periodoCarona;

    @Column(name = "imagem_carona")
    private byte[] imagemCarona;

    @Column(name= "idUsuario", insertable = false, updatable = false)
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name="idUsuario", referencedColumnName = "id_usuario", nullable = false) //Fk IdUsuario na tabela Carona
    private Usuario usuario;

}
