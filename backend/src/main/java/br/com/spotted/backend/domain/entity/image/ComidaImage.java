package br.com.spotted.backend.domain.entity.image;

import br.com.spotted.backend.domain.entity.Comida;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ComidaImage {

    @Id
    @Column(name = "id_image")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Column(name = "url")
    private String url;

    @Column(name = "sequence")
    private int sequence;

    @Column(name = "image_name")
    private String imageName;

    //PRODUCT ID, no caso, o id do item que queremos relacionar
    @Column(name = "id_comida", nullable = false)
    private Long idComida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comidaImage", updatable = false, insertable = false) //FK
    private Comida comida;


}
