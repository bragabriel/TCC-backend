package br.com.spotted.backend.controller.images;

import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.service.images.ImageCrushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class ImageCrushController {

    @Autowired
    private ImageCrushService imageCrushService;

    @PostMapping(value = "/crushUploadImage/{idCrush}", consumes = "multipart/form-data")
    @CrossOrigin()
    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idCrush) throws IOException {
        return ResponseEntity.ok(imageCrushService.createCrushImage(file, idCrush));
    }

    @GetMapping("/crushImage/{idCrush}")
    @CrossOrigin()
    public ResponseEntity findCrushImageByIdImage(@PathVariable Long idCrush){
        return ResponseEntity.ok(imageCrushService.findCrushImageByIdCrush(idCrush));
    }

    @DeleteMapping("/crushDeleteImage/{crushImageId}")
    public ResponseEntity deleteCrushImage(@PathVariable Long crushImageId){
        return ResponseEntity.ok(imageCrushService.deleteCrushImage(crushImageId));
    }

    @PutMapping("/crushUpdateImage/{crushImageId}")
    public ResponseEntity updateCrushImageSequence(@PathVariable Long crushImageId, @RequestBody @NotNull ImageUpdateRequest imageUpdateRequest){
        return ResponseEntity.ok(imageCrushService.updateCrushImageSequence(crushImageId, imageUpdateRequest));
    }
}