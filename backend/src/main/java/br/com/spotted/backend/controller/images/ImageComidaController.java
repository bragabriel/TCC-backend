package br.com.spotted.backend.controller.images;


import br.com.spotted.backend.service.images.ImageComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class ImageComidaController {

    @Autowired
    private ImageComidaService imageComidaService;

    @PostMapping(value = "/upload/{idComida}", consumes = "multipart/form-data")
    @CrossOrigin()
    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idComida) throws IOException {
        return ResponseEntity.ok(imageComidaService.createComidaImage(file, idComida));
    }

    @GetMapping("/comidaImage/{id}")
    @CrossOrigin()
    public ResponseEntity findProductImageByIdProduct(@PathVariable Long id){
        return ResponseEntity.ok(imageComidaService.findComidaImageByIdComida(id));
    }

    @DeleteMapping("/deleteComidaImage/{comidaImageId}")
    public ResponseEntity deleteImage(@PathVariable Long comidaImageId){
        return ResponseEntity.ok(imageComidaService.deleteImage(comidaImageId));
    }

    @PutMapping("/updateComidaImage/{comidaImageId}")
    public ResponseEntity updateImageSequence(@PathVariable Long comidaImageId, @RequestBody @NotNull ProductImageUpdateRequest productImageUpdateRequest){
        return ResponseEntity.ok(imageComidaService.updateImageSequence(comidaImageId, productImageUpdateRequest));
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

