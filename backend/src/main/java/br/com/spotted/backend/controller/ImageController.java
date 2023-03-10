package br.com.spotted.backend.controller;


import br.com.iteris.minishop.domain.dto.product.ProductCreateRequest;
import br.com.iteris.minishop.domain.dto.productImage.ProductImageCreateRequest;
import br.com.iteris.minishop.domain.dto.productImage.ProductImageUpdateRequest;
import br.com.iteris.minishop.domain.entity.Product;
import br.com.iteris.minishop.domain.entity.ProductImage;
import br.com.iteris.minishop.service.ProductImageService;
import br.com.spotted.backend.service.ImageService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@CrossOrigin(origins = "*")
public class ImageController {

    @Autowired
    private ImageService productImageService;

    @PostMapping(value = "/upload/{idProduto}", consumes = "multipart/form-data")
    @CrossOrigin()
    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idProduto) throws IOException {
        return ResponseEntity.ok(productImageService.createUsuarioImage(file, idProduto));
    }

    @GetMapping("/productImage/{id}")
    @CrossOrigin()
    public ResponseEntity findProductImageByIdProduct(@PathVariable Long id){
        return ResponseEntity.ok(productImageService.findProductImageByIdProduct(id));
    }

    @DeleteMapping("/deleteProductImage/{productImageId}")
    public ResponseEntity deleteImage(@PathVariable Long productImageId){
        return ResponseEntity.ok(productImageService.deleteImage(productImageId));
    }

    @PutMapping("/updateProductImage/{productImageId}")
    public ResponseEntity updateImageSequence(@PathVariable Long productImageId, @RequestBody @NotNull ProductImageUpdateRequest productImageUpdateRequest){
        return ResponseEntity.ok(productImageService.updateImageSequence(productImageId, productImageUpdateRequest));
    }


    private String extrairExtensao(String nomeArquivo){
        int i = nomeArquivo.lastIndexOf(".");
        return nomeArquivo.substring(i + 1);
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }
}

