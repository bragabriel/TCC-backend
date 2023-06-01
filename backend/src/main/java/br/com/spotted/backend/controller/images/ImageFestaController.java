package br.com.spotted.backend.controller.images;

import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.service.images.ImageFestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class ImageFestaController {

    @Autowired
    private ImageFestaService imageFestaService;

    @PostMapping(value = "/festaUploadImage/{idFesta}", consumes = "multipart/form-data")
    @CrossOrigin()
    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idFesta) throws IOException {
        return ResponseEntity.ok(imageFestaService.createFestaImage(file, idFesta));
    }

    @GetMapping("/festaImage/{idFesta}")
    @CrossOrigin()
    public ResponseEntity findFestaImageByIdImage(@PathVariable Long idFesta){
        return ResponseEntity.ok(imageFestaService.findFestaImageByIdFesta(idFesta));
    }

    @DeleteMapping("/festaDeleteImage/{festaImageId}")
    public ResponseEntity deleteFestaImage(@PathVariable Long festaImageId){
        return ResponseEntity.ok(imageFestaService.deleteFestaImage(festaImageId));
    }

    @PutMapping("/festaUpdateImage/{festaImageId}")
    public ResponseEntity updateFestaImageSequence(@PathVariable Long festaImageId, @RequestBody @NotNull ImageUpdateRequest imageUpdateRequest){
        return ResponseEntity.ok(imageFestaService.updateFestaImageSequence(festaImageId, imageUpdateRequest));
    }
}