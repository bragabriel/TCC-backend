package br.com.spotted.backend.controller.images;

import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.service.images.ImageApeService;
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
    private ImageApeService imageApeService;

    @PostMapping(value = "/upload/{idApe}", consumes = "multipart/form-data")
    @CrossOrigin()
    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idApe) throws IOException {
        return ResponseEntity.ok(imageApeService.createApeImage(file, idApe));
    }

    @GetMapping("/apeImage/{id}")
    @CrossOrigin()
    public ResponseEntity findApeImageByIdImage(@PathVariable Long id){
        return ResponseEntity.ok(imageApeService.findApeImageByIdApe(id));
    }

    @DeleteMapping("/deleteApeImage/{apeImageId}")
    public ResponseEntity deleteApeImage(@PathVariable Long apeImageId){
        return ResponseEntity.ok(imageApeService.deleteApeImage(apeImageId));
    }

    @PutMapping("/updateApeImage/{apeImageId}")
    public ResponseEntity updateApeImageSequence(@PathVariable Long apeImageId, @RequestBody @NotNull ImageUpdateRequest imageUpdateRequest){
        return ResponseEntity.ok(imageApeService.updateApeImageSequence(apeImageId, imageUpdateRequest));
    }
}