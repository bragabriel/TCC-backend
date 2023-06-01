package br.com.spotted.backend.domain.dto.Image;

import br.com.spotted.backend.domain.entity.image.CaronaImage;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageCaronaResponse {
    private Long idImage;
    private String localUrl;
    private Integer sequence;
    private Long idCarona;
    private String finalUrl;

    public ImageCaronaResponse(CaronaImage caronaImage, String fileName) {
        this.idImage = caronaImage.getIdImage();
        this.localUrl = caronaImage.getUrl();
        this.sequence = caronaImage.getSequence();
        this.idCarona = caronaImage.getIdCarona();
        this.finalUrl = fileName;
    }
}
