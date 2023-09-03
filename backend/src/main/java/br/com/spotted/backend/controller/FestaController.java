package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Festa.FestaArtefatoResponse;
import br.com.spotted.backend.domain.dto.Festa.FestaUpdateRequest;
import br.com.spotted.backend.domain.dto.Festa.FestaCreateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.service.FestaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequiredArgsConstructor
public class FestaController {

    private final FestaService festaService;

    @GetMapping("api/festa")
    public ResponseEntity<ResponseBase<List<FestaArtefatoResponse>>> pesquisar() {
        ResponseBase<List<FestaArtefatoResponse>> retorno = festaService.pesquisar();
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("api/festaPaginado")
    public ResponseEntity pesquisarPaginado(PaginatedSearchRequest searchRequest) {
        ResponseBase<Page<FestaArtefatoResponse>> retorno = festaService.pesquisarPaginado(searchRequest);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping(value = "api/festa/{idFesta}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idFesta) {
        ResponseBase<FestaArtefatoResponse> retorno = festaService.pesquisarPorId(idFesta);
        return ResponseEntity.ok(retorno);
    }

    @PostMapping("api/festa")
    public ResponseEntity cadastrar(@Valid @RequestBody FestaCreateRequest postModel) {
        Long idArtefato = festaService.cadastrar(postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(idArtefato);
    }

    @PutMapping(value = "api/festaAtualizar/{idFesta}")
    public ResponseEntity<FestaArtefatoResponse> atualizarFesta(
            @PathVariable Long idFesta,
            @RequestBody @Valid FestaUpdateRequest festaUpdateRequest
    ){
        var festa = festaService.atualizarFesta(idFesta, festaUpdateRequest);
        return ResponseEntity.ok(festa);
    }

    @PutMapping(value = "api/festaInativar/{idFesta}")
    public ResponseEntity<Void> inativarFesta(
            @PathVariable Long idFesta
    ){
        var festa = festaService.inativarFesta(idFesta);
        return ResponseEntity.ok().build();
    }

}