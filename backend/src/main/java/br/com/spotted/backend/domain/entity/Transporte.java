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
    @Column(name = "id_artefato")
    private Long idArtefato;

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

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //Um transporte pode ter 1-1 artefato
    @JoinColumn(name = "id_artefato")
    private Artefato artefato;

//    @OneToMany(mappedBy = "idTransporte", targetEntity = TransporteImage.class, orphanRemoval = true) //idApe relacionamento na outra table
//    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
//    private List<TransporteImage> listaImagensTransporte = new ArrayList<TransporteImage>();}
}