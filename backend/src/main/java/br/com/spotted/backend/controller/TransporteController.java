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
import java.util.List;

@RestController()
@RequiredArgsConstructor
public class TransporteController {

    private final TransporteService transporteService;

    @GetMapping("api/transporte")
    public ResponseEntity<ResponseBase<List<TransporteResponse>>> pesquisar() {
        ResponseBase<List<TransporteResponse>> retorno = transporteService.pesquisar();
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("api/transportePaginado")
    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest) {
        ResponseBase<Page<TransporteResponse>> retorno = transporteService.pesquisarPaginado(searchRequest);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping(value = "api/transporte/{idTransporte}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idTransporte) {
        ResponseBase<TransporteResponse> retorno = transporteService.pesquisarPorId(idTransporte);
        return ResponseEntity.ok(retorno);
    }

    @PostMapping("api/transporte")
    public ResponseEntity cadastrar(@Valid @RequestBody TransporteCreateRequest postModel) {
        Long idArtefato = transporteService.cadastrar(postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(idArtefato);
    }

    @PutMapping(value = "api/transporteAtualizar/{idTransporte}")
    public ResponseEntity<TransporteResponse> atualizarTransporte(
            @PathVariable Long idTransporte,
            @RequestBody @Valid TransporteUpdateRequest transporteUpdateRequest
    ){
        var transporte = transporteService.atualizarTransporte(idTransporte, transporteUpdateRequest);
        return ResponseEntity.ok(transporte);
    }

    @PutMapping(value = "api/transporteInativar/{idTransporte}")
    public ResponseEntity<Void> inativarTransporte(
            @PathVariable Long idTransporte
    ){
        var transporte = transporteService.inativarTransporte(idTransporte);
        return ResponseEntity.ok().build();
    }
}