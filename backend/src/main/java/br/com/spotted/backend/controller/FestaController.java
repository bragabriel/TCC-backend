package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Festa.FestaCreateRequest;
import br.com.spotted.backend.domain.dto.Festa.FestaResponse;
import br.com.spotted.backend.domain.dto.Festa.FestaUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.service.FestaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequiredArgsConstructor
public class FestaController {

    private final FestaService festaService;

    //Listar Festas com paginação
    @GetMapping("api/festa")
    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest) {

        ResponseBase<Page<FestaResponse>> retorno = festaService.pesquisar(searchRequest);

        return ResponseEntity.ok(retorno);
    }

    //Buscar Festa por Id
    @GetMapping(value = "api/festa/{idFesta}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idFesta) {

        ResponseBase<FestaResponse> retorno = festaService.pesquisarPorId(idFesta);

        return ResponseEntity.ok(retorno);
    }

    //Cadastrar Festa
//    @PostMapping("api/festa")
//    public ResponseEntity cadastrar(@Valid @RequestBody FestaCreateRequest postModel) {
//
//        ResponseBase<FestaResponse> retorno = festaService.cadastrar(postModel);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
//    }

//    //Atualizar Festa
//    @PutMapping(value = "api/festaAtualizar/{idFesta}")
//    public ResponseEntity<FestaResponse> atualizarFesta(
//            @PathVariable Long idFesta,
//            @RequestBody @Valid FestaUpdateRequest festaUpdateRequest
//    ){
//        var festa = festaService.atualizarFesta(idFesta, festaUpdateRequest);
//        return ResponseEntity.ok(festa);
//    }
//
//    //Deletar Festa por Id
//    @DeleteMapping(value = "api/festa/{idFesta}")
//    public ResponseEntity<FestaResponse> deletar(@PathVariable Long idFesta) {
//        var festa = festaService.deletar(idFesta);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(festa);
//    }
}