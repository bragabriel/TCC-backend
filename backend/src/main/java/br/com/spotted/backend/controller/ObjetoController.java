package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Objeto.ObjetoCreateRequest;
import br.com.spotted.backend.domain.dto.Objeto.ObjetoResponse;
import br.com.spotted.backend.domain.dto.Objeto.ObjetoUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.service.ObjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequiredArgsConstructor
public class ObjetoController {

    private final ObjetoService objetoService;

    //Listar Objeto com paginação
    @GetMapping("api/objeto")
    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest) {

        ResponseBase<Page<ObjetoResponse>> retorno = objetoService.pesquisar(searchRequest);

        return ResponseEntity.ok(retorno);
    }

    //Buscar Objeto por Id
    @GetMapping(value = "api/objeto/{idObjeto}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idObjeto) {

        ResponseBase<ObjetoResponse> retorno = objetoService.pesquisarPorId(idObjeto);

        return ResponseEntity.ok(retorno);
    }

    //Cadastrar Objeto
    @PostMapping("api/objeto")
    public ResponseEntity cadastrar(@Valid @RequestBody ObjetoCreateRequest postModel) {

        ResponseBase<ObjetoResponse> retorno = objetoService.cadastrar(postModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }

    //Deletar Objeto por Id
    @DeleteMapping(value = "api/objeto/{idObjeto}")
    public ResponseEntity<ObjetoResponse> deletar(@PathVariable Long idObjeto) {
        var objeto = objetoService.deletar(idObjeto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(objeto);
    }

    //Atualizar Objeto
    @PutMapping(value = "api/objetoAtualizar/{idObjeto}")
    public ResponseEntity<ObjetoResponse> atualizarObjeto(
            @PathVariable Long idObjeto,
            @RequestBody @Valid ObjetoUpdateRequest objetoUpdateRequest
    ){
        var objeto = objetoService.atualizarObjeto(idObjeto, objetoUpdateRequest);
        return ResponseEntity.ok(objeto);
    }
}