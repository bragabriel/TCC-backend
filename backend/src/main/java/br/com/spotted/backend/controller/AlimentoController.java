package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Alimento.AlimentoCreateRequest;
import br.com.spotted.backend.domain.dto.Alimento.AlimentoResponse;
import br.com.spotted.backend.domain.dto.Alimento.AlimentoUpdateRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.service.AlimentoService;
import br.com.spotted.backend.service.ArtefatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController()
@RequiredArgsConstructor
public class AlimentoController {

    private final AlimentoService alimentoService;

    //Listar Alimentos com paginação
    @GetMapping("api/alimento")
    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest) {

        ResponseBase<Page<AlimentoResponse>> retorno = alimentoService.pesquisar(searchRequest);

        return ResponseEntity.ok(retorno);
    }

    //Buscar Alimento por Id
    @GetMapping(value = "api/alimento/{idAlimento}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idAlimento) {

        ResponseBase<AlimentoResponse> retorno = alimentoService.pesquisarPorId(idAlimento);

        return ResponseEntity.ok(retorno);
    }

    //Cadastrar Alimento
    @PostMapping("api/alimento")
    public ResponseEntity cadastrar(@Valid @RequestBody AlimentoCreateRequest postModel) {

        ResponseBase<AlimentoResponse> retorno = alimentoService.cadastrar(postModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }

    //Deletar Alimento por Id
    @DeleteMapping(value = "api/alimento/{idAlimento}")
    public ResponseEntity<AlimentoResponse> deletar(@PathVariable Long idAlimento) {
        var alimento = alimentoService.deletar(idAlimento);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(alimento);
    }

    //Atualizar Alimento
    @PutMapping(value = "api/alimentoAtualizar/{idAlimento}")
    public ResponseEntity<AlimentoResponse> atualizarAlimento(
            @PathVariable Long idAlimento,
            @RequestBody @Valid AlimentoUpdateRequest alimentoUpdateRequest
    ){
        var alimento = alimentoService.atualizarAlimento(idAlimento, alimentoUpdateRequest);
        return ResponseEntity.ok(alimento);
    }
}
