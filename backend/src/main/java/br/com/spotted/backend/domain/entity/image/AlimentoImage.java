package br.com.spotted.backend.domain.entity.image;

import br.com.spotted.backend.domain.entity.Alimento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AlimentoImage {

    @Id
    @Column(name = "id_imageAlimento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Column(name = "url_imageAlimento")
    private String url;

    @Column(name = "sequence_imageAlimento")
    private int sequence;

    @Column(name = "fileName_imageAlimento")
    private String fileName;

    //PRODUCT ID, no caso, o id do item que queremos relacionar
    @Column(name = "id_alimento", nullable = false)
    private Long idAlimento;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imageAlimento", updatable = false, insertable = false) //FK
    private Alimento alimento;
}