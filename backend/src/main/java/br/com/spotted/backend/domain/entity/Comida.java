package br.com.spotted.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="Comida")
@AllArgsConstructor
@NoArgsConstructor
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComida")
    private Long idComida;

    @Column(name = "nomeUsuario", nullable = false)
    private String nomeComida;

    @Column(name = "tipoComida", nullable = false)
    private String tipoComida;

    @Column(name = "imagemComida")
    private byte[] imagemUsuario;

    @Column(name= "idUsuario", insertable = false, updatable = false)
    private Long idUsuario;

    @ManyToOne //Muitas COMIDAS podem ter apenas 1 USUARIO
    @JoinColumn(name="idUsuario", referencedColumnName = "idUsuario", nullable = false) //Fk IdUsuario na tabela Comida
    private Usuario usuario;

}
