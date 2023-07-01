package br.com.spotted.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@Entity
@Table(name="Alimento")
@AllArgsConstructor
@NoArgsConstructor
public class Alimento{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artefato")
    private Long idArtefato;

    @Column(name = "tipo_alimento", nullable = false)
    private String tipoAlimento;

    @Column(name = "marca_alimento")
    private String marcaAlimento;

    @Column(name = "sabor_alimento")
    private String saborAlimento;

    @Column(name = "unidade_alimento")
    private String unidadeAlimento;

    @Column(name = "preco_alimento")
    private Double precoAlimento;

    @Column(name = "oferta_alimento")
    private String ofertaAlimento;

    @OneToOne //Um alimento pode ter 1-1 artefato
    @MapsId
    @JoinColumn(name="id_artefato", referencedColumnName = "id_artefato", updatable = false, insertable = false) //Fk IdUsuario na tabela Alimento
    private Artefato artefato;

//    @OneToMany(mappedBy = "idAlimento", targetEntity = AlimentoImage.class, orphanRemoval = true) //idAlimento relacionamento na outra table
//    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
//    private List<AlimentoImage> listaImagensAlimento = new ArrayList<AlimentoImage>();
}