package br.com.spotted.backend.controller.images;

import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.service.images.ImageTransporteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;


@RestController
@CrossOrigin(origins = "*")
public class ImageTransporteController {

    @Autowired
    private ImageTransporteService imageTransporteService;

    @PostMapping(value = "/transporteUploadImage/{idTransporte}", consumes = "multipart/form-data")
    @CrossOrigin()
    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idTransporte) throws IOException {
        return ResponseEntity.ok(imageTransporteService.createTransporteImage(file, idTransporte));
    }

    @GetMapping("/transporteImage/{idTransporte}")
    @CrossOrigin()
    public ResponseEntity findTransporteImageByIdImage(@PathVariable Long idTransporte){
        return ResponseEntity.ok(imageTransporteService.findTransporteImageByIdTransporte(idTransporte));
    }

    @DeleteMapping("/transporteDeleteImage/{transporteImageId}")
    public ResponseEntity deleteTransporteImage(@PathVariable Long transporteImageId){
        return ResponseEntity.ok(imageTransporteService.deleteTransporteImage(transporteImageId));
    }

    @PutMapping("/transporteUpdateImage/{transporteImageId}")
    public ResponseEntity updateTransporteImageSequence(@PathVariable Long transporteImageId, @RequestBody @NotNull ImageUpdateRequest imageUpdateRequest){
        return ResponseEntity.ok(imageTransporteService.updateTransporteImageSequence(transporteImageId, imageUpdateRequest));
    }
}