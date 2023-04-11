package br.com.spotted.backend.domain.dto.Image;

import br.com.spotted.backend.domain.entity.image.ApeImage;
import br.com.spotted.backend.domain.entity.image.ComidaImage;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageApeResponse {
    private Long idImage;
    private String localUrl;
    private Integer sequence;
    private Long idApe;
    private String finalUrl;

    public ImageApeResponse(ApeImage apeImage, String fileName) {
        this.idImage = apeImage.getIdImage();
        this.localUrl = apeImage.getUrl();
        this.sequence = apeImage.getSequence();
        this.idApe = apeImage.getIdApe();
        this.finalUrl = fileName;
    }
}
