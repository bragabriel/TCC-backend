package br.com.spotted.backend.domain.dto.Image;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ImageUpdateRequest {

    @NotEmpty
    private String url;

    @NotEmpty
    private Integer sequence;

    @NotNull
    private Long idProduct;

    @NotNull
    private Long idProductImage;

    @NotEmpty
    private String finalUrl;

}
