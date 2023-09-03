package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Objeto.ObjetoCreateRequest;
import br.com.spotted.backend.domain.dto.Objeto.ObjetoArtefatoResponse;
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
import java.util.List;

@RestController()
@RequiredArgsConstructor
public class ObjetoController {

    private final ObjetoService objetoService;

    @GetMapping("api/objeto")
    public ResponseEntity<ResponseBase<List<ObjetoArtefatoResponse>>> pesquisar() {
        ResponseBase<List<ObjetoArtefatoResponse>> retorno = objetoService.pesquisar();
        return ResponseEntity.ok(retorno);
    }
    @GetMapping("api/objetoPaginado")
    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest) {
        ResponseBase<Page<ObjetoArtefatoResponse>> retorno = objetoService.pesquisarPaginado(searchRequest);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping(value = "api/objeto/{idObjeto}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idObjeto) {
        ResponseBase<ObjetoArtefatoResponse> retorno = objetoService.pesquisarPorId(idObjeto);
        return ResponseEntity.ok(retorno);
    }

    @PostMapping("api/objeto")
    public ResponseEntity cadastrar(@Valid @RequestBody ObjetoCreateRequest postModel) {
        Long idArtefato = objetoService.cadastrar(postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(idArtefato);
    }

    @PutMapping(value = "api/objetoAtualizar/{idObjeto}")
    public ResponseEntity<ObjetoArtefatoResponse> atualizarObjeto(
            @PathVariable Long idObjeto,
            @RequestBody @Valid ObjetoUpdateRequest objetoUpdateRequest
    ){
        var objeto = objetoService.atualizarObjeto(idObjeto, objetoUpdateRequest);
        return ResponseEntity.ok(objeto);
    }

    @PutMapping(value = "api/objetoInativar/{idObjeto}")
    public ResponseEntity<Void> inativarObjeto(
            @PathVariable Long idObjeto
    ){
        var objeto = objetoService.inativarObjeto(idObjeto);
        return ResponseEntity.ok().build();
    }
}