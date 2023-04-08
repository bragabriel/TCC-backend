package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Comida.ComidaCreateRequest;
import br.com.spotted.backend.domain.dto.Comida.ComidaResponse;
import br.com.spotted.backend.domain.dto.Comida.ComidaUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.service.ComidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController()
@RequiredArgsConstructor
public class ComidaController {

    private final ComidaService comidaService;

    //Listar Comidas com paginação
    @GetMapping("api/comida")
    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest) {

        ResponseBase<Page<ComidaResponse>> retorno = comidaService.pesquisar(searchRequest);

        return ResponseEntity.ok(retorno);
    }

    //Buscar Comida por Id
    @GetMapping(value = "api/comida/{idComida}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idComida) {

        ResponseBase<ComidaResponse> retorno = comidaService.pesquisarPorId(idComida);

        return ResponseEntity.ok(retorno);
    }

    //Cadastrar Comida
    @PostMapping("api/comida")
    public ResponseEntity cadastrar(@Valid @RequestBody ComidaCreateRequest postModel) {

        ResponseBase<ComidaResponse> retorno = comidaService.cadastrar(postModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }

    //Deletar Comida por Id
    @DeleteMapping(value = "api/comida/{idComida}")
    public ResponseEntity<ComidaResponse> deletar(@PathVariable Long idComida) {
        var comida = comidaService.deletar(idComida);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(comida);
    }

    //Atualizar Comida
    @PutMapping(value = "api/comidaAtualizar/{idComida}")
    public ResponseEntity<ComidaResponse> atualizarComida(
            @PathVariable Long idComida,
            @RequestBody @Valid ComidaUpdateRequest comidaUpdateRequest
    ){
        var comida = comidaService.atualizarComida(idComida, comidaUpdateRequest);
        return ResponseEntity.ok(comida);
    }
}
