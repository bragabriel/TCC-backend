package br.com.spotted.backend.controller;

import br.com.spotted.backend.domain.dto.Evento.EventoArtefatoResponse;
import br.com.spotted.backend.domain.dto.Evento.EventoUpdateRequest;
import br.com.spotted.backend.domain.dto.Evento.EventoCreateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @GetMapping("api/evento")
    public ResponseEntity<ResponseBase<List<EventoArtefatoResponse>>> pesquisar() {
        ResponseBase<List<EventoArtefatoResponse>> retorno = eventoService.pesquisar();
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("api/eventoPaginado")
    public ResponseEntity pesquisarPaginado(PaginatedSearchRequest searchRequest) {
        ResponseBase<Page<EventoArtefatoResponse>> retorno = eventoService.pesquisarPaginado(searchRequest);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping(value = "api/evento/{idEvento}")
    public ResponseEntity pesquisarPorId(@PathVariable Long idEvento) {
        ResponseBase<EventoArtefatoResponse> retorno = eventoService.pesquisarPorId(idEvento);
        return ResponseEntity.ok(retorno);
    }

    @PostMapping("api/evento")
    public ResponseEntity cadastrar(@Valid @RequestBody EventoCreateRequest postModel) {
        Long idArtefato = eventoService.cadastrar(postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(idArtefato);
    }

    @PutMapping(value = "api/eventoAtualizar/{idEvento}")
    public ResponseEntity<EventoArtefatoResponse> atualizarEvento(
            @PathVariable Long idEvento,
            @RequestBody @Valid EventoUpdateRequest eventoUpdateRequest
    ){
        var evento = eventoService.atualizarEvento(idEvento, eventoUpdateRequest);
        return ResponseEntity.ok(evento);
    }

    @PutMapping(value = "api/eventoInativar/{idEvento}")
    public ResponseEntity<Void> inativarEvento(
            @PathVariable Long idEvento
    ){
        var evento = eventoService.inativarEvento(idEvento);
        return ResponseEntity.ok().build();
    }
}