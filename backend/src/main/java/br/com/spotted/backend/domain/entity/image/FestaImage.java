package br.com.spotted.backend.domain.entity.image;

import br.com.spotted.backend.domain.entity.Festa;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class FestaImage {

    @Id
    @Column(name = "id_imageFesta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Column(name = "url_imageFesta")
    private String url;

    @Column(name = "sequence_imageFesta")
    private int sequence;

    @Column(name = "fileName_imageFesta")
    private String fileName;

    //PRODUCT ID, no caso, o id do item que queremos relacionar
    @Column(name = "id_festa", nullable = false)
    private Long idFesta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imageFesta", updatable = false, insertable = false) //FK
    private Festa festa;

}
