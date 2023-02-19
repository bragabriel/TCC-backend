package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Ape.ApeCreateRequest;
import br.com.spotted.backend.domain.dto.Ape.ApeResponse;
import br.com.spotted.backend.domain.dto.Ape.ApeUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.service.ApeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequiredArgsConstructor
public class ApeController {

    private final ApeService apeService;

    //Listar Comidas com paginação
    @GetMapping("api/ape")
    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest) {

        ResponseBase<Page<ApeResponse>> retorno = apeService.pesquisar(searchRequest);

        return ResponseEntity.ok(retorno);
    }

    //Buscar Ape por Id
    @GetMapping(value = "api/ape/{idApe}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idApe) {

        ResponseBase<ApeResponse> retorno = apeService.pesquisarPorId(idApe);

        return ResponseEntity.ok(retorno);
    }

    //Cadastrar Ape
    @PostMapping("api/ape")
    public ResponseEntity cadastrar(@Valid @RequestBody ApeCreateRequest postModel) {

        ResponseBase<ApeResponse> retorno = apeService.cadastrar(postModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }

    //Deletar Ape por Id
    @DeleteMapping(value = "api/ape/{idApe}")
    public ResponseEntity<ApeResponse> deletar(@PathVariable Long idApe) {
        var ape = apeService.deletar(idApe);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ape);
    }

    //Atualizar Ape
    @PutMapping(value = "api/apeAtualizar/{idApe}")
    public ResponseEntity<ApeResponse> atualizarApe(
            @PathVariable Long idApe,
            @RequestBody @Valid ApeUpdateRequest apeUpdateRequest
    ){
        var ape = apeService.atualizarApe(idApe, apeUpdateRequest);
        return ResponseEntity.ok(ape);
    }
}