package br.com.spotted.backend.domain.dto.Image;

import br.com.spotted.backend.domain.entity.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ImageResponse {

    private Long idImage;
    private String url;
    private Integer sequence;
    private Long idItem;
    private String finalUrl;

    public ImageResponse(Image image, String fileName) {
        this.idImage = image.getId();
        this.url = image.getUrl();
        this.sequence = image.getSequence();
        this.idItem = image.getItemId();
        this.finalUrl = fileName;
    }
}


