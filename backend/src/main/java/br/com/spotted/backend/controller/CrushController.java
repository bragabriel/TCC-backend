package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Crush.CrushCreateRequest;
import br.com.spotted.backend.domain.dto.Crush.CrushResponse;
import br.com.spotted.backend.domain.dto.Crush.CrushUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.service.CrushService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequiredArgsConstructor
public class CrushController {

    private final CrushService crushService;

    //Listar Crush com paginação
    @GetMapping("api/crush")
    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest) {

        ResponseBase<Page<CrushResponse>> retorno = crushService.pesquisar(searchRequest);

        return ResponseEntity.ok(retorno);
    }

    //Buscar Crush por Id
    @GetMapping(value = "api/crush/{idCrush}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idCrush) {

        ResponseBase<CrushResponse> retorno = crushService.pesquisarPorId(idCrush);

        return ResponseEntity.ok(retorno);
    }

    //Cadastrar Crush
    @PostMapping("api/crush")
    public ResponseEntity cadastrar(@Valid @RequestBody CrushCreateRequest postModel) {

        ResponseBase<CrushResponse> retorno = crushService.cadastrar(postModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }

    //Deletar Crush por 'id'
    @DeleteMapping(value = "api/crush/{idCrush}")
    public ResponseEntity<CrushResponse> deletar(@PathVariable Long idCrush) {
        var crush = crushService.deletar(idCrush);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(crush);
    }

    //Atualizar Carona
    @PutMapping(value = "api/crushAtualizar/{idCrush}")
    public ResponseEntity<CrushResponse> atualizarCrush(
            @PathVariable Long idCrush,
            @RequestBody @Valid CrushUpdateRequest crushUpdateRequest
    ){
        var crush = crushService.atualizarCrush(idCrush, crushUpdateRequest);
        return ResponseEntity.ok(crush);
    }
}
