package br.com.spotted.backend.domain.dto.Image;

import br.com.spotted.backend.domain.entity.image.EmpregoImage;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageEstagioResponse {
    private Long idImage;
    private String localUrl;
    private Integer sequence;
    private Long idEstagio;
    private String finalUrl;

    public ImageEstagioResponse(EmpregoImage empregoImage, String fileName) {
        this.idImage = empregoImage.getIdImage();
        this.localUrl = empregoImage.getUrl();
        this.sequence = empregoImage.getSequence();
        this.idEstagio = empregoImage.getIdEstagio();
        this.finalUrl = fileName;
    }
}
