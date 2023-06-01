package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Estagio.EstagioCreateRequest;
import br.com.spotted.backend.domain.dto.Estagio.EstagioResponse;
import br.com.spotted.backend.domain.dto.Estagio.EstagioUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.service.EstagioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequiredArgsConstructor
public class EstagioController {

    private final EstagioService estagioService;

    @GetMapping("api/estagio")
    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest) {

        ResponseBase<Page<EstagioResponse>> retorno = estagioService.pesquisar(searchRequest);

        return ResponseEntity.ok(retorno);
    }

    @GetMapping(value = "api/estagio/{idEstagio}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idEstagio) {

        ResponseBase<EstagioResponse> retorno = estagioService.pesquisarPorId(idEstagio);

        return ResponseEntity.ok(retorno);
    }

    @PostMapping("api/estagio")
    public ResponseEntity cadastrar(@Valid @RequestBody EstagioCreateRequest postModel) {

        ResponseBase<EstagioResponse> retorno = estagioService.cadastrar(postModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }

    @DeleteMapping(value = "api/estagio/{idEstagio}")
    public ResponseEntity<EstagioResponse> deletar(@PathVariable Long idEstagio) {
        var estagio = estagioService.deletar(idEstagio);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(estagio);
    }

    @PutMapping(value = "api/estagioAtualizar/{idEstagio}")
    public ResponseEntity<EstagioResponse> atualizarEstagio(
            @PathVariable Long idEstagio,
            @RequestBody @Valid EstagioUpdateRequest estagioUpdateRequest
    ){
        var estagio = estagioService.atualizarEstagio(idEstagio, estagioUpdateRequest);
        return ResponseEntity.ok(estagio);
    }
}
