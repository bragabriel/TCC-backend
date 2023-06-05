package br.com.spotted.backend.domain.entity.image;

import br.com.spotted.backend.domain.entity.Moradia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MoradiaImage {

    @Id
    @Column(name = "id_imageMoradia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Column(name = "url_imageMoradia")
    private String url;

    @Column(name = "sequence_imageMoradia")
    private int sequence;

    @Column(name = "fileName_imageMoradia")
    private String fileName;

    //PRODUCT ID, no caso, o id do item que queremos relacionar
    @Column(name = "id_moradia", nullable = false)
    private Long idMoradia;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imageMoradia", updatable = false, insertable = false) //FK
    private Moradia moradia;
}
