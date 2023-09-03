package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Emprego.EmpregoArtefatoResponse;
import br.com.spotted.backend.domain.dto.Emprego.EmpregoUpdateRequest;
import br.com.spotted.backend.domain.dto.Emprego.EmpregoCreateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.service.EmpregoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequiredArgsConstructor
public class EmpregoController {

    private final EmpregoService empregoService;

    @GetMapping("api/emprego")
    public ResponseEntity<ResponseBase<List<EmpregoArtefatoResponse>>> pesquisar() {
        ResponseBase<List<EmpregoArtefatoResponse>> retorno = empregoService.pesquisar();
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("api/empregoPaginado")
    public ResponseEntity pesquisarPaginado(PaginatedSearchRequest searchRequest) {
        ResponseBase<Page<EmpregoArtefatoResponse>> retorno = empregoService.pesquisarPaginado(searchRequest);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping(value = "api/emprego/{idEmprego}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idEmprego) {
        ResponseBase<EmpregoArtefatoResponse> retorno = empregoService.pesquisarPorId(idEmprego);
        return ResponseEntity.ok(retorno);
    }

    @PostMapping("api/emprego")
    public ResponseEntity cadastrar(@Valid @RequestBody EmpregoCreateRequest postModel) {
        Long idArtefato = empregoService.cadastrar(postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(idArtefato);
    }

    @PutMapping(value = "api/empregoAtualizar/{idEmprego}")
    public ResponseEntity<EmpregoArtefatoResponse> atualizarEmprego(
            @PathVariable Long idEmprego,
            @RequestBody @Valid EmpregoUpdateRequest empregoUpdateRequest
    ){
        var emprego = empregoService.atualizarEmprego(idEmprego, empregoUpdateRequest);
        return ResponseEntity.ok(emprego);
    }

    @PutMapping(value = "api/empregoInativar/{idEmprego}")
    public ResponseEntity<Void> inativarEmprego(
            @PathVariable Long idEmprego
    ){
        var emprego = empregoService.inativarEmprego(idEmprego);
        return ResponseEntity.ok().build();
    }
}