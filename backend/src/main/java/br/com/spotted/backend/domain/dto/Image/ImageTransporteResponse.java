package br.com.spotted.backend.domain.dto.Image;

import br.com.spotted.backend.domain.entity.image.TransporteImage;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageTransporteResponse {
    private Long idImage;
    private String localUrl;
    private Integer sequence;
    private Long idTransporte;
    private String finalUrl;

    public ImageTransporteResponse(TransporteImage transporteImage, String fileName) {
        this.idImage = transporteImage.getIdImage();
        this.localUrl = transporteImage.getUrl();
        this.sequence = transporteImage.getSequence();
        this.idTransporte = transporteImage.getIdTransporte();
        this.finalUrl = fileName;
    }
}
