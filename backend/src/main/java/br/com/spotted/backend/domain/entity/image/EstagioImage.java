package br.com.spotted.backend.domain.entity.image;

import br.com.spotted.backend.domain.entity.Crush;
import br.com.spotted.backend.domain.entity.Estagio;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class EstagioImage {

    @Id
    @Column(name = "id_imageEstagio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Column(name = "url_imageEstagio")
    private String url;

    @Column(name = "sequence_imageEstagio")
    private int sequence;

    @Column(name = "fileName_imageEstagio")
    private String fileName;

    //PRODUCT ID, no caso, o id do item que queremos relacionar
    @Column(name = "id_estagio", nullable = false)
    private Long idEstagio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imageEstagio", updatable = false, insertable = false) //FK
    private Estagio estagio;

}
