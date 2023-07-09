package br.com.spotted.backend.domain.dto.Image;

import br.com.spotted.backend.domain.entity.Imagem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageResponse {

    private Long idImage;
    private String localUrl;
    private Integer sequence;
    private Long idArtefato;
    private String finalUrl;

    public ImageResponse(Imagem imagem, String fileName) {
        this.idImage = imagem.getIdImage();
        this.localUrl = imagem.getUrl();
        this.sequence = imagem.getSequence();
        this.idArtefato = imagem.getIdArtefato();
        this.finalUrl = fileName;
    }
}


