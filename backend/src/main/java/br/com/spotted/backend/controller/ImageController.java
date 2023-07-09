package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;

import br.com.spotted.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;


@RestController
@CrossOrigin(origins = "*")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/uploadImage/{idArtefato}", consumes = "multipart/form-data")
    @CrossOrigin()
    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idArtefato) throws IOException {
        return ResponseEntity.ok(imageService.createImage(file, idArtefato));
    }

    @GetMapping("{idArtefato}")
    @CrossOrigin()
    public ResponseEntity findAlimentoImageByIdImage(@PathVariable Long idArtefato){
        return ResponseEntity.ok(imageService.findImageByIdArtefato(idArtefato));
    }

    @DeleteMapping("{imageId}")
    public ResponseEntity deleteAlimentoImage(@PathVariable Long imageId){
        return ResponseEntity.ok(imageService.deleteImage(imageId));
    }

    @PutMapping("/updateImage/{imageId}")
    public ResponseEntity updateAlimentoImageSequence(@PathVariable Long imageId, @RequestBody @NotNull ImageUpdateRequest imageUpdateRequest){
        return ResponseEntity.ok(imageService.updateImageSequence(imageId, imageUpdateRequest));
    }
}