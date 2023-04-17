package br.com.spotted.backend.domain.dto.Image;

import br.com.spotted.backend.domain.entity.image.FestaImage;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageFestaResponse {
    private Long idImage;
    private String localUrl;
    private Integer sequence;
    private Long idFesta;
    private String finalUrl;

    public ImageFestaResponse(FestaImage festaImageImage, String fileName) {
        this.idImage = festaImageImage.getIdImage();
        this.localUrl = festaImageImage.getUrl();
        this.sequence = festaImageImage.getSequence();
        this.idFesta = festaImageImage.getIdFesta();
        this.finalUrl = fileName;
    }
}
