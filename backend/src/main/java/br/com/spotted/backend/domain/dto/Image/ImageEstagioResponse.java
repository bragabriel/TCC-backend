package br.com.spotted.backend.domain.dto.Image;

import br.com.spotted.backend.domain.entity.image.EstagioImage;
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

    public ImageEstagioResponse(EstagioImage estagioImage, String fileName) {
        this.idImage = estagioImage.getIdImage();
        this.localUrl = estagioImage.getUrl();
        this.sequence = estagioImage.getSequence();
        this.idEstagio = estagioImage.getIdEstagio();
        this.finalUrl = fileName;
    }
}
