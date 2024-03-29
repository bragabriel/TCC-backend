package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Moradia.MoradiaCreateRequest;
import br.com.spotted.backend.domain.dto.Moradia.MoradiaArtefatoResponse;
import br.com.spotted.backend.domain.dto.Moradia.MoradiaUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.service.MoradiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController()
@RequiredArgsConstructor
public class MoradiaController {

    private final MoradiaService moradiaService;

    @GetMapping("api/moradia")
    public ResponseEntity<ResponseBase<List<MoradiaArtefatoResponse>>> pesquisar() {
        ResponseBase<List<MoradiaArtefatoResponse>> retorno = moradiaService.pesquisar();
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("api/moradiaPaginado")
    public ResponseEntity pesquisarPaginado(PaginatedSearchRequest searchRequest) {
        ResponseBase<Page<MoradiaArtefatoResponse>> retorno = moradiaService.pesquisarPaginado(searchRequest);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping(value = "api/moradia/{idMoradia}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idMoradia) {
        ResponseBase<MoradiaArtefatoResponse> retorno = moradiaService.pesquisarPorId(idMoradia);
        return ResponseEntity.ok(retorno);
    }

    @PostMapping("api/moradia")
    public ResponseEntity cadastrar(@Valid @RequestBody MoradiaCreateRequest postModel) {
        Long idArtefato = moradiaService.cadastrar(postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(idArtefato);
    }

    @PutMapping(value = "api/moradiaAtualizar/{idMoradia}")
    public ResponseEntity<MoradiaArtefatoResponse> atualizarMoradia(
            @PathVariable Long idMoradia,
            @RequestBody @Valid MoradiaUpdateRequest moradiaUpdateRequest
    ){
        var moradia = moradiaService.atualizarMoradia(idMoradia, moradiaUpdateRequest);
        return ResponseEntity.ok(moradia);
    }

    @PutMapping(value = "api/moradiaInativar/{idMoradia}")
    public ResponseEntity<Void> inativarMoradia(
            @PathVariable Long idMoradia
    ){
        var moradia = moradiaService.inativarMoradia(idMoradia);
        return ResponseEntity.ok().build();
    }
}