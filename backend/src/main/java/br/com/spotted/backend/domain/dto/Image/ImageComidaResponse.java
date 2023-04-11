package br.com.spotted.backend.domain.dto.Image;

import br.com.spotted.backend.domain.entity.image.ComidaImage;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageComidaResponse {

    private Long idImage;
    private String localUrl;
    private Integer sequence;
    private Long idComida;
    private String finalUrl;

    public ImageComidaResponse(ComidaImage comidaImage, String fileName) {
        this.idImage = comidaImage.getIdImage();
        this.localUrl = comidaImage.getUrl();
        this.sequence = comidaImage.getSequence();
        this.idComida = comidaImage.getIdComida();
        this.finalUrl = fileName;
    }
}


