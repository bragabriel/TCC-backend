package br.com.spotted.backend.domain.entity;

import br.com.spotted.backend.domain.entity.image.AlimentoImage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="Alimento")
@AllArgsConstructor
@NoArgsConstructor
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alimento")
    private Long idAlimento;

    @Column(name = "titulo_alimento", nullable = false)
    private String tituloAlimento;

    @Column(name = "descricao_alimento", nullable = false)
    private String descricaoAlimento;

    @Column(name = "tipo_alimento", nullable = false)
    private String tipoAlimento;

    @Column(name = "marca_alimento")
    private String marcaAlimento;

    @Column(name = "preco_alimento")
    private Double precoAlimento;

    @Column(name = "oferta_alimento")
    private String ofertaAlimento;

    @Column(name= "id_usuario")
    private Long idUsuario;

    @JsonIgnore
    @ManyToOne //Muitas COMIDAS podem ter apenas 1 USUARIO
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario", updatable = false, insertable = false) //Fk IdUsuario na tabela Alimento
    private Usuario usuario;

    @OneToMany(mappedBy = "idAlimento", targetEntity = AlimentoImage.class, orphanRemoval = true) //idAlimento relacionamento na outra table
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<AlimentoImage> listaImagensAlimento = new ArrayList<AlimentoImage>();;
}