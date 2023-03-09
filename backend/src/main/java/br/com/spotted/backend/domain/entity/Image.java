package br.com.spotted.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "URL")
    private String url;

    @Column(name = "Sequence")
    private int sequence;

    //PRODUCT ID, no caso, o id do item que queremos relacionar
    @Column(name = "ProductId")
    private Long itemId;

    @Column(name = "ImageName")
    private String imageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductId", updatable = false, insertable = false)
    private Product product;


}
