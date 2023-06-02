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
public class ImageApeController {

    @Autowired
    private ImageMoradiaService imageMoradiaService;

    @PostMapping(value = "/apeUploadImage/{idApe}", consumes = "multipart/form-data")
    @CrossOrigin()
    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idApe) throws IOException {
        return ResponseEntity.ok(imageMoradiaService.createApeImage(file, idApe));
    }

    @GetMapping("/apeImage/{idApe}")
    @CrossOrigin()
    public ResponseEntity findApeImageByIdImage(@PathVariable Long idApe){
        return ResponseEntity.ok(imageMoradiaService.findApeImageByIdApe(idApe));
    }

    @DeleteMapping("/apeDeleteImage/{apeImageId}")
    public ResponseEntity deleteApeImage(@PathVariable Long apeImageId){
        return ResponseEntity.ok(imageMoradiaService.deleteApeImage(apeImageId));
    }

    @PutMapping("/apeUpdateImage/{apeImageId}")
    public ResponseEntity updateApeImageSequence(@PathVariable Long apeImageId, @RequestBody @NotNull ImageUpdateRequest imageUpdateRequest){
        return ResponseEntity.ok(imageMoradiaService.updateApeImageSequence(apeImageId, imageUpdateRequest));
    }
}