package br.com.spotted.backend.domain.entity.image;

import br.com.spotted.backend.domain.entity.Comida;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ComidaImage {

    @Id
    @Column(name = "id_imageComida")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Column(name = "url_imageComida")
    private String url;

    @Column(name = "sequence_imageComida")
    private int sequence;

    @Column(name = "fileName_imageComida")
    private String fileName;

    //PRODUCT ID, no caso, o id do item que queremos relacionar
    @Column(name = "id_comida", nullable = false)
    private Long idComida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imageComida", updatable = false, insertable = false) //FK
    private Comida comida;


}