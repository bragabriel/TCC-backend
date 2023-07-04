package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Transporte.TransporteCreateRequest;
import br.com.spotted.backend.domain.dto.Transporte.TransporteResponse;
import br.com.spotted.backend.domain.dto.Transporte.TransporteUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.service.TransporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequiredArgsConstructor
public class TransporteController {

    private final TransporteService transporteService;

    @GetMapping("api/transporte")
    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest) {

        ResponseBase<Page<TransporteResponse>> retorno = transporteService.pesquisar(searchRequest);

        return ResponseEntity.ok(retorno);
    }

    @GetMapping(value = "api/transporte/{idTransporte}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idTransporte) {

        ResponseBase<TransporteResponse> retorno = transporteService.pesquisarPorId(idTransporte);

        return ResponseEntity.ok(retorno);
    }

    @PostMapping("api/transporte")
    public ResponseEntity cadastrar(@Valid @RequestBody TransporteCreateRequest postModel) {

        ResponseBase<TransporteResponse> retorno = transporteService.cadastrar(postModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }

//    @PutMapping(value = "api/transporteAtualizar/{idTransporte}")
//    public ResponseEntity<TransporteResponse> atualizarTransporte(
//            @PathVariable Long idTransporte,
//            @RequestBody @Valid TransporteUpdateRequest transporteUpdateRequest
//    ){
//        var transporte = transporteService.atualizarTransporte(idTransporte, transporteUpdateRequest);
//        return ResponseEntity.ok(transporte);
//    }
//
//    @DeleteMapping(value = "api/transporte/{idTransporte}")
//    public ResponseEntity<TransporteResponse> deletar(@PathVariable Long idTransporte) {
//        var transporte = transporteService.deletar(idTransporte);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transporte);
//    }
}