package br.com.spotted.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="Transporte")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transporte {

    @Id
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
}