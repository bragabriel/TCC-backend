package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioCreateRequest;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioLoginRequest;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioResponse;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioUpdateRequest_NomeSobrenome;
import br.com.spotted.backend.service.ImagemUsuarioService;
import br.com.spotted.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController()
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ImagemUsuarioService imagemUsuarioService;

    @GetMapping("api/usuario")
    public ResponseEntity<ResponseBase<List<UsuarioResponse>>> pesquisar() {
        ResponseBase<List<UsuarioResponse>> retorno = usuarioService.pesquisar();
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("api/usuariPaginado")
    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest) {
        ResponseBase<Page<UsuarioResponse>> retorno = usuarioService.pesquisarPaginado(searchRequest);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping(value = "api/usuario/{idUsuario}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idUsuario) {
        ResponseBase<UsuarioResponse> retorno = usuarioService.pesquisarPorId(idUsuario);
        return ResponseEntity.ok(retorno);
    }

    @PostMapping("api/usuario")
    public ResponseEntity cadastrar(@Valid @RequestBody UsuarioCreateRequest postModel) {
        Long idUsuario = usuarioService.cadastrar(postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(idUsuario);
    }

    @PostMapping("api/usuarioLogar")
    public ResponseEntity logar(@Valid @RequestBody UsuarioLoginRequest usuarioLoginRequest) {
        var retorno = usuarioService.logar(usuarioLoginRequest.getEmail(), usuarioLoginRequest.getSenha());
        return ResponseEntity.ok(retorno);
    }

    @PutMapping(value = "api/usuarioAtualizar/{idUsuario}")
    public ResponseEntity<UsuarioResponse> atualizarNomeSobrenome(
            @PathVariable Long idUsuario,
            @RequestBody @Valid UsuarioUpdateRequest_NomeSobrenome usuarioUpdateRequest
    ){
        var usuario = usuarioService.atualizarNomeSobrenome(idUsuario, usuarioUpdateRequest);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping(value = "api/usuario/{idUsuario}")
    public ResponseEntity<UsuarioResponse> deletar(@PathVariable Long idUsuario) {
        var usuario = usuarioService.deletar(idUsuario);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuario);
    }

    //Usuario Imagens
    @PostMapping(value = "api/usuarioUploadImage/{idUsuario}", consumes = "multipart/form-data")
    @CrossOrigin()
    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idUsuario) throws IOException {
        return ResponseEntity.ok(imagemUsuarioService.createUsuarioImage(file, idUsuario));
    }

    @DeleteMapping("api/usuarioDeleteImage/{idUsuario}")
    public ResponseEntity deleteUsuarioImage(@PathVariable Long idUsuario){
        return ResponseEntity.ok(imagemUsuarioService.deleteUsuarioImage(idUsuario));
    }
}

