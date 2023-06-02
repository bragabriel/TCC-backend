package br.com.spotted.backend.controller.images;

import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.service.images.ImageMoradiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class ImageMoradiaController {

    @Autowired
    private ImageMoradiaService imageMoradiaService;

    @PostMapping(value = "/moradiaUploadImage/{idMoradia}", consumes = "multipart/form-data")
    @CrossOrigin()
    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idMoradia) throws IOException {
        return ResponseEntity.ok(imageMoradiaService.createMoradiaImage(file, idMoradia));
    }

    @GetMapping("/moradiaImage/{idMoradia}")
    @CrossOrigin()
    public ResponseEntity findMoradiaImageByIdImage(@PathVariable Long idMoradia){
        return ResponseEntity.ok(imageMoradiaService.findMoradiaImageByIdMoradia(idMoradia));
    }

    @DeleteMapping("/moradiaDeleteImage/{moradiaImageId}")
    public ResponseEntity deleteMoradiaImage(@PathVariable Long moradiaImageId){
        return ResponseEntity.ok(imageMoradiaService.deleteMoradiaImage(moradiaImageId));
    }

    @PutMapping("/moradiaUpdateImage/{moradiaImageId}")
    public ResponseEntity updateMoradiaImageSequence(@PathVariable Long moradiaImageId, @RequestBody @NotNull ImageUpdateRequest imageUpdateRequest){
        return ResponseEntity.ok(imageMoradiaService.updateMoradiaImageSequence(moradiaImageId, imageUpdateRequest));
    }
}