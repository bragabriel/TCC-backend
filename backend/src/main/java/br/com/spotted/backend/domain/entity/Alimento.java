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
@AttributeOverride(name = "titulo", column = @Column(name = "titulo_alimento", nullable = false))
@AttributeOverride(name = "descricao", column = @Column(name = "descricao_alimento", nullable = false))
public class Alimento extends Global{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alimento")
    private Long idAlimento;

//    @Column(name = "titulo_alimento", nullable = false)
//    private String titulo;
//
//    @Column(name = "descricao_alimento", nullable = false)
//    private String descricao;

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

    public String getTituloPai() {
        return super.getTitulo();
    }

    public void setTitulo(String titulo) {
        super.setTitulo(titulo);
    }

    public String getDescricaoPai() {
        return super.getDescricao();
    }

    public void setDescricao(String descricao) {
        super.setDescricao(descricao);
    }
}