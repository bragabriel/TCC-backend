package br.com.spotted.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Image {

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
    @Column(name = "id_item")
    private Long idItem;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_image", updatable = false, insertable = false) //FK
    private Usuario usuario;


}
