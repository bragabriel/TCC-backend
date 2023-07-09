package br.com.spotted.backend.domain.entity.image;

import br.com.spotted.backend.domain.entity.Artefato;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Image {

    @Id
    @Column(name = "id_image")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Column(name = "url_imageAlimento")
    private String url;

    @Column(name = "sequence_imageAlimento")
    private int sequence;

    @Column(name = "fileName_imageAlimento")
    private String fileName;

    @Column(name = "id_artefato", nullable = false)
    private Long idArtefato;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_artefato", updatable = false, insertable = false) //FK
    private Artefato artefato;
}