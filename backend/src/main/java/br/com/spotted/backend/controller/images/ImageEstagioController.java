package br.com.spotted.backend.controller.images;

import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.service.images.ImageEstagioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class ImageEstagioController {

    @Autowired
    private ImageEstagioService imageEstagioService;

    @PostMapping(value = "/estagioUploadImage/{idEstagio}", consumes = "multipart/form-data")
    @CrossOrigin()
    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idEstagio) throws IOException {
        return ResponseEntity.ok(imageEstagioService.createEstagioImage(file, idEstagio));
    }

    @GetMapping("/estagioImage/{idEstagio}")
    @CrossOrigin()
    public ResponseEntity findEstagioImageByIdImage(@PathVariable Long idEstagio){
        return ResponseEntity.ok(imageEstagioService.findEstagioImageByIdEstagio(idEstagio));
    }

    @DeleteMapping("/estagioDeleteImage/{estagioImageId}")
    public ResponseEntity deleteEstagioImage(@PathVariable Long estagioImageId){
        return ResponseEntity.ok(imageEstagioService.deleteEstagioImage(estagioImageId));
    }

    @PutMapping("/estagioUpdateImage/{estagioImageId}")
    public ResponseEntity updateEstagioImageSequence(@PathVariable Long estagioImageId, @RequestBody @NotNull ImageUpdateRequest imageUpdateRequest){
        return ResponseEntity.ok(imageEstagioService.updateEstagioImageSequence(estagioImageId, imageUpdateRequest));
    }
}
