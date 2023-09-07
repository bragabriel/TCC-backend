package br.com.spotted.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="Evento")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Evento {

    @Id
    @Column(name = "id_artefato")
    private Long idArtefato;

    @Column(name = "localizacao_evento")
    private String localizacaoEvento;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //Uma evento pode ter 1-1 artefato
    @JoinColumn(name = "id_artefato")
    private Artefato artefato;
}
