package br.com.spotted.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="Emprego")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Emprego {

    @Id
    @Column(name = "id_artefato")
    private Long idArtefato;

    @Column(name = "localizacao_emprego", nullable = false)
    private String localizacaoEmprego;

    @Column(name = "requisitos_emprego")
    private String requisitosEmprego;

    @Column(name = "salario_emprego")
    private Integer salarioEmprego;

    @Column(name = "beneficios_emprego")
    private String beneficiosEmprego;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //Um emprego pode ter 1-1 artefato
    @JoinColumn(name = "id_artefato")
    private Artefato artefato;
}
