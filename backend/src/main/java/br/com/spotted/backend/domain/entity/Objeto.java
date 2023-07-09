package br.com.spotted.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="Objeto")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Objeto {

    @Id
    @Column(name = "id_artefato")
    private Long idArtefato;

    @Column(name = "localizacaoAchado_objeto")
    private String localizacaoAchadoObjeto;

    @Column(name = "localizacaoAtual_objeto")
    private String localizacaoAtualObjeto;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //Um objeto pode ter 1-1 artefato
    @JoinColumn(name = "id_artefato")
    private Artefato artefato;
}