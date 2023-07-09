package br.com.spotted.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="Festa")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Festa {

    @Id
    @Column(name = "id_artefato")
    private Long idArtefato;

    @Column(name = "localizacao_festa")
    private String localizacaoFesta;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //Uma festa pode ter 1-1 artefato
    @JoinColumn(name = "id_artefato")
    private Artefato artefato;
}
