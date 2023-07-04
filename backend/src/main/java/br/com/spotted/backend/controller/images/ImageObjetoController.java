//package br.com.spotted.backend.controller.images;
//
//import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
//import br.com.spotted.backend.service.images.ImageObjetoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//import java.io.IOException;
//
//@RestController
//@CrossOrigin(origins = "*")
//public class ImageObjetoController {
//
//    @Autowired
//    private ImageObjetoService imageObjetoService;
//
//    @PostMapping(value = "/objetoUploadImage/{idObjeto}", consumes = "multipart/form-data")
//    @CrossOrigin()
//    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idObjeto) throws IOException {
//        return ResponseEntity.ok(imageObjetoService.createObjetoImage(file, idObjeto));
//    }
//
//    @GetMapping("/objetoImage/{idObjeto}")
//    @CrossOrigin()
//    public ResponseEntity findObjetoImageByIdImage(@PathVariable Long idObjeto){
//        return ResponseEntity.ok(imageObjetoService.findObjetoImageByIdObjeto(idObjeto));
//    }
//
//    @DeleteMapping("/objetoDeleteImage/{objetoImageId}")
//    public ResponseEntity deleteObjetoImage(@PathVariable Long objetoImageId){
//        return ResponseEntity.ok(imageObjetoService.deleteObjetoImage(objetoImageId));
//    }
//
//    @PutMapping("/objetoUpdateImage/{objetoImageId}")
//    public ResponseEntity updateObjetoImageSequence(@PathVariable Long objetoImageId, @RequestBody @NotNull ImageUpdateRequest imageUpdateRequest){
//        return ResponseEntity.ok(imageObjetoService.updateObjetoImageSequence(objetoImageId, imageUpdateRequest));
//    }
//}