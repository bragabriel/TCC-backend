package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Emprego.EmpregoCreateRequest;
import br.com.spotted.backend.domain.dto.Emprego.EmpregoResponse;
import br.com.spotted.backend.domain.dto.Emprego.EmpregoUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.service.EmpregoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequiredArgsConstructor
public class EstagioController {

    private final EmpregoService empregoService;

    @GetMapping("api/estagio")
    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest) {

        ResponseBase<Page<EmpregoResponse>> retorno = empregoService.pesquisar(searchRequest);

        return ResponseEntity.ok(retorno);
    }

    @GetMapping(value = "api/estagio/{idEstagio}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idEstagio) {

        ResponseBase<EmpregoResponse> retorno = empregoService.pesquisarPorId(idEstagio);

        return ResponseEntity.ok(retorno);
    }

    @PostMapping("api/estagio")
    public ResponseEntity cadastrar(@Valid @RequestBody EmpregoCreateRequest postModel) {

        ResponseBase<EmpregoResponse> retorno = empregoService.cadastrar(postModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }

//    @PutMapping(value = "api/estagioAtualizar/{idEstagio}")
//    public ResponseEntity<EmpregoResponse> atualizarEstagio(
//            @PathVariable Long idEstagio,
//            @RequestBody @Valid EmpregoUpdateRequest empregoUpdateRequest
//    ){
//        var estagio = empregoService.atualizarEstagio(idEstagio, empregoUpdateRequest);
//        return ResponseEntity.ok(estagio);
//    }
//
//    @DeleteMapping(value = "api/estagio/{idEstagio}")
//    public ResponseEntity<EmpregoResponse> deletar(@PathVariable Long idEstagio) {
//        var estagio = empregoService.deletar(idEstagio);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(estagio);
//    }
}