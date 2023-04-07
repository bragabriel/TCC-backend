package br.com.spotted.backend.domain.dto.Image;

import br.com.spotted.backend.domain.entity.image.ComidaImage;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageResponse {

    private Long idImage;
    private String url;
    private Integer sequence;
    private Long idItem;
    private String finalUrl;

    public ImageResponse(ComidaImage comidaImage, String fileName) {
        this.idImage = comidaImage.getIdImage();
        this.url = comidaImage.getUrl();
        this.sequence = comidaImage.getSequence();
        this.idItem = comidaImage.getIdItem();
        this.finalUrl = fileName;
    }
}


