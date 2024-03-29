package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Alimento.AlimentoCreateRequest;
import br.com.spotted.backend.domain.dto.Alimento.AlimentoArtefatoResponse;
import br.com.spotted.backend.domain.dto.Alimento.AlimentoUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.service.AlimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController()
@RequiredArgsConstructor
public class AlimentoController {

    @Autowired
    private final AlimentoService alimentoService;

    @GetMapping("api/alimento")
    public ResponseEntity<ResponseBase<List<AlimentoArtefatoResponse>>> pesquisar() {
        ResponseBase<List<AlimentoArtefatoResponse>> retorno = alimentoService.pesquisar();
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("api/alimentoPaginado")
    public ResponseEntity pesquisarPaginado(PaginatedSearchRequest searchRequest) {
        ResponseBase<Page<AlimentoArtefatoResponse>> retorno = alimentoService.pesquisarPaginado(searchRequest);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping(value = "api/alimento/{idAlimento}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idAlimento) {
        ResponseBase<AlimentoArtefatoResponse> retorno = alimentoService.pesquisarPorId(idAlimento);
        return ResponseEntity.ok(retorno);
    }

    @PostMapping("api/alimento")
    public ResponseEntity cadastrar(@Valid @RequestBody AlimentoCreateRequest postModel) {
        Long idArtefato = alimentoService.cadastrar(postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(idArtefato);
    }

    @PutMapping(value = "api/alimentoAtualizar/{idAlimento}")
    public ResponseEntity<AlimentoArtefatoResponse> atualizarAlimento(
            @PathVariable Long idAlimento,
            @RequestBody @Valid AlimentoUpdateRequest alimentoUpdateRequest
    ){
        var alimento = alimentoService.atualizarAlimento(idAlimento, alimentoUpdateRequest);
        return ResponseEntity.ok(alimento);
    }

    @PutMapping(value = "api/alimentoInativar/{idAlimento}")
    public ResponseEntity<Void> inativarAlimento(
            @PathVariable Long idAlimento
    ){
        var alimento = alimentoService.inativarAlimento(idAlimento);
        return ResponseEntity.ok().build();
    }
}