package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;

import br.com.spotted.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@CrossOrigin(origins = "*")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/api/uploadImage/{idArtefato}", consumes = "multipart/form-data")
    @CrossOrigin()
    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idArtefato) throws Exception {
        return ResponseEntity.ok(imageService.createImage(file, idArtefato));
    }

    @GetMapping("/api/{idArtefato}")
    @CrossOrigin()
    public ResponseEntity findImageByIdImage(@PathVariable Long idArtefato){
        return ResponseEntity.ok(imageService.findImageByIdArtefato(idArtefato));
    }

    @DeleteMapping("/api/{imageId}")
    public ResponseEntity deleteImage(@PathVariable Long imageId){
        return ResponseEntity.ok(imageService.deleteImage(imageId));
    }

    @PutMapping("/api/updateImage/{imageId}")
    public ResponseEntity updateImageSequence(@PathVariable Long imageId, @RequestBody @NotNull ImageUpdateRequest imageUpdateRequest){
        return ResponseEntity.ok(imageService.updateImageSequence(imageId, imageUpdateRequest));
    }
}