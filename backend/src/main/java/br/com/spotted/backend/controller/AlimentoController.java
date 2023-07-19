package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Alimento.AlimentoCreateRequest;
import br.com.spotted.backend.domain.dto.Alimento.AlimentoResponse;
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
    public ResponseEntity<ResponseBase<List<AlimentoResponse>>> pesquisar() {
        ResponseBase<List<AlimentoResponse>> retorno = alimentoService.pesquisar();
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("api/alimentoPaginado")
    public ResponseEntity pesquisarPaginado(PaginatedSearchRequest searchRequest) {
        ResponseBase<Page<AlimentoResponse>> retorno = alimentoService.pesquisarPaginado(searchRequest);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping(value = "api/alimento/{idAlimento}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idAlimento) {
        ResponseBase<AlimentoResponse> retorno = alimentoService.pesquisarPorId(idAlimento);
        return ResponseEntity.ok(retorno);
    }

    @PostMapping("api/alimento")
    public ResponseEntity cadastrar(@Valid @RequestBody AlimentoCreateRequest postModel) {
        ResponseBase<AlimentoResponse> retorno = alimentoService.cadastrar(postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }

    @PutMapping(value = "api/alimentoAtualizar/{idAlimento}")
    public ResponseEntity<AlimentoResponse> atualizarAlimento(
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

    //Deletar Alimento por Id
//    @DeleteMapping(value = "api/alimento/{idAlimento}")
//    public ResponseEntity<Void> deletar(@PathVariable Long idAlimento) {
//        alimentoService.deletar(idAlimento);
//        return ResponseEntity.ok().build();
//    }
}
