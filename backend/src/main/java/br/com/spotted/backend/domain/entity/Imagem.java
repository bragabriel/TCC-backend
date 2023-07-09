package br.com.spotted.backend.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Imagem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Column(name = "url")
    private String url;

    @Column(name = "sequence")
    private int sequence;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "id_artefato", nullable = false)
    private Long idArtefato;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_artefato", updatable = false, insertable = false) //FK
    private Artefato artefato;
}