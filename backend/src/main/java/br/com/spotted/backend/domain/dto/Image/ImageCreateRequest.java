package br.com.spotted.backend.domain.dto.Image;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ImageCreateRequest {

    @NotNull
    private Long idItem;
}

