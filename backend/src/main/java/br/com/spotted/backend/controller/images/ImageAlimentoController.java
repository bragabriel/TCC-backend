//package br.com.spotted.backend.controller.images;
//
//import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
//import br.com.spotted.backend.service.images.ImageAlimentoService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//import java.io.IOException;
//
//
//@RestController
//@CrossOrigin(origins = "*")
//public class ImageAlimentoController {
//
//    @Autowired
//    private ImageAlimentoService imageAlimentoService;
//
//    @PostMapping(value = "/alimentoUploadImage/{idAlimento}", consumes = "multipart/form-data")
//    @CrossOrigin()
//    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idAlimento) throws IOException {
//        return ResponseEntity.ok(imageAlimentoService.createAlimentoImage(file, idAlimento));
//    }
//
//    @GetMapping("/alimentoImage/{idAlimento}")
//    @CrossOrigin()
//    public ResponseEntity findAlimentoImageByIdImage(@PathVariable Long idAlimento){
//        return ResponseEntity.ok(imageAlimentoService.findAlimentoImageByIdAlimento(idAlimento));
//    }
//
//    @DeleteMapping("/alimentoDeleteImage/{alimentoImageId}")
//    public ResponseEntity deleteAlimentoImage(@PathVariable Long alimentoImageId){
//        return ResponseEntity.ok(imageAlimentoService.deleteAlimentoImage(alimentoImageId));
//    }
//
//    @PutMapping("/alimentoUpdateImage/{alimentoImageId}")
//    public ResponseEntity updateAlimentoImageSequence(@PathVariable Long alimentoImageId, @RequestBody @NotNull ImageUpdateRequest imageUpdateRequest){
//        return ResponseEntity.ok(imageAlimentoService.updateAlimentoImageSequence(alimentoImageId, imageUpdateRequest));
//    }
//}
//
