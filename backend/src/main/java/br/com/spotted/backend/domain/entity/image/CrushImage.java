package br.com.spotted.backend.domain.entity.image;

import br.com.spotted.backend.domain.entity.Comida;
import br.com.spotted.backend.domain.entity.Crush;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CrushImage {

    @Id
    @Column(name = "id_imageCrush")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Column(name = "url_imageCrush")
    private String url;

    @Column(name = "sequence_imageCrush")
    private int sequence;

    @Column(name = "fileName_imageCrush")
    private String fileName;

    //PRODUCT ID, no caso, o id do item que queremos relacionar
    @Column(name = "id_crush", nullable = false)
    private Long idCrush;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imageCrush", updatable = false, insertable = false) //FK
    private Crush crush;

}
