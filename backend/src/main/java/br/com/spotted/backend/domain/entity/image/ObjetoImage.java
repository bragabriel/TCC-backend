package br.com.spotted.backend.domain.entity.image;

import br.com.spotted.backend.domain.entity.Crush;
import br.com.spotted.backend.domain.entity.Objeto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ObjetoImage {
    @Id
    @Column(name = "id_imageObjeto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Column(name = "url_imageObjeto")
    private String url;

    @Column(name = "sequence_imageObjeto")
    private int sequence;

    @Column(name = "fileName_imageObjeto")
    private String fileName;

    //PRODUCT ID, no caso, o id do item que queremos relacionar
    @Column(name = "id_objeto", nullable = false)
    private Long idObjeto;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imageObjeto", updatable = false, insertable = false) //FK
    private Objeto objeto;
}
