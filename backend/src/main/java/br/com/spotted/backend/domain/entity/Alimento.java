package br.com.spotted.backend.domain.entity;

import br.com.spotted.backend.service.ArtefatoService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="Alimento")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alimento{

    @Id
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

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //Um alimento pode ter 1-1 artefato
    @JoinColumn(name = "id_artefato")
    private Artefato artefato;
}