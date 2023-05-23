package br.com.spotted.backend.controller.images;

import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.service.images.ImageCaronaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;


@RestController
@CrossOrigin(origins = "*")
public class ImageCaronaController {

    @Autowired
    private ImageCaronaService imageCaronaService;

    @PostMapping(value = "/caronaUploadImage/{idCarona}", consumes = "multipart/form-data")
    @CrossOrigin()
    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idCarona) throws IOException {
        return ResponseEntity.ok(imageCaronaService.createCaronaImage(file, idCarona));
    }

    @GetMapping("/caronaImage/{idCarona}")
    @CrossOrigin()
    public ResponseEntity findCaronaImageByIdImage(@PathVariable Long idCarona){
        return ResponseEntity.ok(imageCaronaService.findCaronaImageByIdCarona(idCarona));
    }

    @DeleteMapping("/caronaDeleteImage/{caronaImageId}")
    public ResponseEntity deleteCaronaImage(@PathVariable Long caronaImageId){
        return ResponseEntity.ok(imageCaronaService.deleteCaronaImage(caronaImageId));
    }

    @PutMapping("/caronaUpdateImage/{caronaImageId}")
    public ResponseEntity updateCaronaImageSequence(@PathVariable Long caronaImageId, @RequestBody @NotNull ImageUpdateRequest imageUpdateRequest){
        return ResponseEntity.ok(imageCaronaService.updateCaronaImageSequence(caronaImageId, imageUpdateRequest));
    }
}