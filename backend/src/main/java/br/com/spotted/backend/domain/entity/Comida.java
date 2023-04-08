package br.com.spotted.backend.domain.entity;

import br.com.spotted.backend.domain.entity.image.ComidaImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "preco_comida")
    private Double precoComida;

    @Column(name = "oferta_comida")
    private String ofertaComida;

    @Column(name= "id_usuario")
    private Long idUsuario;

    @ManyToOne //Muitas COMIDAS podem ter apenas 1 USUARIO
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario", updatable = false, insertable = false) //Fk IdUsuario na tabela Comida
    private Usuario usuario;

    @OneToMany(mappedBy = "idComida", targetEntity = ComidaImage.class, orphanRemoval = true) //idComida relacionamento na outra table
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<ComidaImage> listaImagensComida = new ArrayList<ComidaImage>();;
}