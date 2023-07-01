package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoCreateRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.service.ArtefatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequiredArgsConstructor
public class ArtefatoController {
    private final ArtefatoService artefatoService;

    @GetMapping("api/artefato")
    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest, String tipo) {
        ResponseBase<Page<ArtefatoResponse>> retorno = artefatoService.pesquisar(searchRequest, tipo);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping(value = "api/artefato/{idArtefato}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idArtefato) {
        ResponseBase<ArtefatoResponse> retorno = artefatoService.pesquisarPorId(idArtefato);
        return ResponseEntity.ok(retorno);
    }

    @PostMapping("api/artefato")
    public ResponseEntity cadastrar(@Valid @RequestBody ArtefatoCreateRequest postModel) {
        ResponseBase<ArtefatoResponse> retorno = artefatoService.cadastrar(postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }

    @DeleteMapping(value = "api/artefato/{idArtefato}")
    public ResponseEntity<ArtefatoResponse> deletar(@PathVariable Long idArtefato) {
        var artefato = artefatoService.deletar(idArtefato);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(artefato);
    }

    @PutMapping(value = "api/artefatoAtualizar/{idArtefato}")
    public ResponseEntity<ArtefatoResponse> atualizarArtefato(
            @PathVariable Long idArtefato,
            @RequestBody @Valid ArtefatoUpdateRequest artefatoUpdateRequest
    ){
        var artefato = artefatoService.atualizarArtefato(idArtefato, artefatoUpdateRequest);
        return ResponseEntity.ok(artefato);
    }
}
