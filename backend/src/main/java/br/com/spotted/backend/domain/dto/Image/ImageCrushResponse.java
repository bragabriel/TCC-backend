package br.com.spotted.backend.domain.dto.Image;

import br.com.spotted.backend.domain.entity.image.CrushImage;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageCrushResponse {
    private Long idImage;
    private String localUrl;
    private Integer sequence;
    private Long idCrush;
    private String finalUrl;

    public ImageCrushResponse(CrushImage crushImage, String fileName) {
        this.idImage = crushImage.getIdImage();
        this.localUrl = crushImage.getUrl();
        this.sequence = crushImage.getSequence();
        this.idCrush = crushImage.getIdCrush();
        this.finalUrl = fileName;
    }
}
