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
    @Column(name = "id_comida")
    private Long idComida;

    @Column(name = "titulo_comida", nullable = false)
    private String tituloComida;

    @Column(name = "descricao_comida", nullable = false)
    private String descricaoComida;

    @Column(name = "tipo_comida", nullable = false)
    private String tipoComida;

    @Column(name = "marca_comida")
    private String marcaComida;

    @Column(name = "oferta_comida")
    private String ofertaComida;

    @Column(name = "imagem_comida")
    private byte[] imagemComida;

    @Column(name= "id_usuario", insertable = false, updatable = false)
    private Long idUsuario;

    @ManyToOne //Muitas COMIDAS podem ter apenas 1 USUARIO
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario", nullable = false) //Fk IdUsuario na tabela Comida
    private Usuario usuario;

}
