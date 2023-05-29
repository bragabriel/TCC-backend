package br.com.spotted.backend.domain.entity.image;

import br.com.spotted.backend.domain.entity.Ape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ApeImage {

    @Id
    @Column(name = "id_imageApe")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Column(name = "url_imageApe")
    private String url;

    @Column(name = "sequence_imageApe")
    private int sequence;

    @Column(name = "fileName_imageApe")
    private String fileName;

    //PRODUCT ID, no caso, o id do item que queremos relacionar
    @Column(name = "id_ape", nullable = false)
    private Long idApe;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imageApe", updatable = false, insertable = false) //FK
    private Ape ape;
}
