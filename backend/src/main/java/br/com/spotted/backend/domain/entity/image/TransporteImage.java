package br.com.spotted.backend.domain.entity.image;

import br.com.spotted.backend.domain.entity.Transporte;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TransporteImage {
    @Id
    @Column(name = "id_imageTransporte")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Column(name = "url_imageTransporte")
    private String url;

    @Column(name = "sequence_imageTransporte")
    private int sequence;

    @Column(name = "fileName_imageTransporte")
    private String fileName;

    @Column(name = "id_transporte", nullable = false)
    private Long idTransporte;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imageTransporte", updatable = false, insertable = false) //FK
    private Transporte transporte;
}
