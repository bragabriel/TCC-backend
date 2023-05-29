package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioCreateRequest;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioLoginRequest;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioResponse;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioUpdateRequest_NomeSobrenome;
import br.com.spotted.backend.domain.entity.Usuario;
import br.com.spotted.backend.service.UsuarioService;
import br.com.spotted.backend.service.images.ImageUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;


@RestController()
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ImageUsuarioService imageUsuarioService;

    //Listar Usuarios com paginação
    @GetMapping("api/usuario")
    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest) {

        ResponseBase<Page<UsuarioResponse>> retorno = usuarioService.pesquisar(searchRequest);

        return ResponseEntity.ok(retorno);
    }

    //Buscar Usuario por Id
    @GetMapping(value = "api/usuario/{idUsuario}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idUsuario) {

        ResponseBase<UsuarioResponse> retorno = usuarioService.pesquisarPorId(idUsuario);
        //ResponseBase<Usuario> retorno = usuarioService.pesquisarPorId(idUsuario);

        return ResponseEntity.ok(retorno);
    }

    //Cadastrar Usuario
    @PostMapping("api/usuario")
    public ResponseEntity cadastrar(@Valid @RequestBody UsuarioCreateRequest postModel) {

        ResponseBase<UsuarioResponse> retorno = usuarioService.cadastrar(postModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
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

    //Deletar Usuario por Id
    @DeleteMapping(value = "api/usuario/{idUsuario}")
    public ResponseEntity<UsuarioResponse> deletar(@PathVariable Long idUsuario) {
        var usuario = usuarioService.deletar(idUsuario);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuario);
    }

    //Usuario Imagens
    @PostMapping(value = "api/usuarioUploadImage/{idUsuario}", consumes = "multipart/form-data")
    @CrossOrigin()
    public ResponseEntity uploadImage(@RequestPart("files") MultipartFile[] file, @Valid @PathVariable Long idUsuario) throws IOException {
        return ResponseEntity.ok(imageUsuarioService.createUsuarioImage(file, idUsuario));
    }

    @DeleteMapping("api/usuarioDeleteImage/{idUsuario}")
    public ResponseEntity deleteUsuarioImage(@PathVariable Long idUsuario){
        return ResponseEntity.ok(imageUsuarioService.deleteUsuarioImage(idUsuario));
    }
}

