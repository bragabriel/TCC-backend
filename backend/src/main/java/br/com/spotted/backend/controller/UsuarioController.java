package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioCreateRequest;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioResponse;
import br.com.spotted.backend.domain.entity.Usuario;
import br.com.spotted.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController()
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

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

        return ResponseEntity.ok(retorno);
    }

    //Cadastrar Usuario
    @PostMapping("api/usuario")
    public ResponseEntity cadastrar(@Valid @RequestBody UsuarioCreateRequest postModel) {

        ResponseBase<UsuarioResponse> retorno = usuarioService.cadastrar(postModel);

        return ResponseEntity.ok(retorno);
    }

    //Deletar Usuario por Id
    @DeleteMapping(value = "api/usuario/{idUsuario}")
    public ResponseEntity<UsuarioResponse> deletar(@PathVariable Long idUsuario) {
        var usuario = usuarioService.deletar(idUsuario);
        return ResponseEntity.ok(usuario);
    }

    //Logar Usuario por nome & senha
    @GetMapping(value = "api/usuarioLogar/{nome}/{senha}")
    public ResponseEntity pesquisarPorId(@PathVariable String nome, @PathVariable String senha) {

        var retorno = usuarioService.logar(nome, senha);

        return ResponseEntity.ok(retorno);
    }

}

