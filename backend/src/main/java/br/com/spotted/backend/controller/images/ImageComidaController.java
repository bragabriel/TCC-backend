package br.com.spotted.backend.controller.images;

import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.service.images.ImageComidaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;


@RestController
@CrossOrigin(origins = "*")
public class ImageComidaController {

    @Autowired
    private ImageComidaService imageComidaService;

    @PostMapping(value = "/comidaUploadImage/{idComida}", consumes = "multipart/form-data")
    @CrossOrigin()
    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idComida) throws IOException {
        return ResponseEntity.ok(imageComidaService.createComidaImage(file, idComida));
    }

    @GetMapping("/comidaImage/{idComida}")
    @CrossOrigin()
    public ResponseEntity findComidaImageByIdImage(@PathVariable Long idComida){
        return ResponseEntity.ok(imageComidaService.findComidaImageByIdComida(idComida));
    }

    @DeleteMapping("/comidaDeleteImage/{comidaImageId}")
    public ResponseEntity deleteComidaImage(@PathVariable Long comidaImageId){
        return ResponseEntity.ok(imageComidaService.deleteComidaImage(comidaImageId));
    }

    @PutMapping("/comidaUpdateImage/{comidaImageId}")
    public ResponseEntity updateComidaImageSequence(@PathVariable Long comidaImageId, @RequestBody @NotNull ImageUpdateRequest imageUpdateRequest){
        return ResponseEntity.ok(imageComidaService.updateComidaImageSequence(comidaImageId, imageUpdateRequest));
    }
}

