package br.com.spotted.backend.domain.dto.Image;

import br.com.spotted.backend.domain.entity.image.ObjetoImage;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageObjetoResponse {
    private Long idImage;
    private String localUrl;
    private Integer sequence;
    private Long idObjeto;
    private String finalUrl;

    public ImageObjetoResponse(ObjetoImage objetoImage, String fileName) {
        this.idImage = objetoImage.getIdImage();
        this.localUrl = objetoImage.getUrl();
        this.sequence = objetoImage.getSequence();
        this.idObjeto = objetoImage.getIdObjeto();
        this.finalUrl = fileName;
    }
}
