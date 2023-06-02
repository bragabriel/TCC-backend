package br.com.spotted.backend.domain.dto.Image;

import br.com.spotted.backend.domain.entity.image.MoradiaImage;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageMoradiaResponse {
    private Long idImage;
    private String localUrl;
    private Integer sequence;
    private Long idMoradia;
    private String finalUrl;

    public ImageMoradiaResponse(MoradiaImage moradiaImage, String fileName) {
        this.idImage = moradiaImage.getIdImage();
        this.localUrl = moradiaImage.getUrl();
        this.sequence = moradiaImage.getSequence();
        this.idMoradia = moradiaImage.getIdMoradia();
        this.finalUrl = fileName;
    }
}
