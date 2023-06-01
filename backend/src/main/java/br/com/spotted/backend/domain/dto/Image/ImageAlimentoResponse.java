package br.com.spotted.backend.domain.dto.Image;

import br.com.spotted.backend.domain.entity.image.AlimentoImage;
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

    public ImageAlimentoResponse(AlimentoImage alimentoImage, String fileName) {
        this.idImage = alimentoImage.getIdImage();
        this.localUrl = alimentoImage.getUrl();
        this.sequence = alimentoImage.getSequence();
        this.idAlimento = alimentoImage.getIdAlimento();
        this.finalUrl = fileName;
    }
}


