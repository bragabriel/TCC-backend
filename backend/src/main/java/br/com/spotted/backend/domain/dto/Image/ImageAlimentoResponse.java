package br.com.spotted.backend.domain.dto.Image;

import br.com.spotted.backend.domain.entity.image.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageAlimentoResponse {

    private Long idImage;
    private String localUrl;
    private Integer sequence;
    private Long idAlimento;
    private String finalUrl;

    public ImageAlimentoResponse(Image image, String fileName) {
        this.idImage = image.getIdImage();
        this.localUrl = image.getUrl();
        this.sequence = image.getSequence();
        this.idAlimento = image.getIdAlimento();
        this.finalUrl = fileName;
    }
}


