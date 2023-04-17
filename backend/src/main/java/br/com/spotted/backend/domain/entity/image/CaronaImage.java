package br.com.spotted.backend.domain.entity.image;

import br.com.spotted.backend.domain.entity.Carona;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CaronaImage {
    @Id
    @Column(name = "id_imageCarona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Column(name = "url_imageCarona")
    private String url;

    @Column(name = "sequence_imageCarona")
    private int sequence;

    @Column(name = "fileName_imageCarona")
    private String fileName;

    @Column(name = "id_carona", nullable = false)
    private Long idCarona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imageCarona", updatable = false, insertable = false) //FK
    private Carona carona;
}
