package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoInactiveRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.Evento.EventoCreateRequest;
import br.com.spotted.backend.domain.dto.Evento.EventoArtefatoResponse;
import br.com.spotted.backend.domain.dto.Evento.EventoUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Evento;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.TipoArtefato;
import br.com.spotted.backend.exception.EventoNotFoundException;
import br.com.spotted.backend.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;
    @Autowired
    private final ArtefatoService artefatoService;

    public ResponseBase<List<EventoArtefatoResponse>> pesquisar() {
        Iterable<Evento> eventos = eventoRepository.findAllEventosWithArtefatoAtivo();
        List<EventoArtefatoResponse> eventoArtefatoResponse = new ArrayList<>();

        for (Evento evento : eventos) {
            eventoArtefatoResponse.add(new EventoArtefatoResponse(evento));
        }

        return new ResponseBase<>(eventoArtefatoResponse);
    }

    public ResponseBase<Page<EventoArtefatoResponse>> pesquisarPaginado(PaginatedSearchRequest searchRequest) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Evento> eventoPage = eventoRepository.findAll(searchRequest.parseToPageRequest());

        Page<EventoArtefatoResponse> apeResponsePage = eventoPage.map(EventoArtefatoResponse::new);
        return new ResponseBase<>(apeResponsePage);
    }

    public ResponseBase<EventoArtefatoResponse> pesquisarPorId(Long id) {

        Optional<Evento> apeOptional = eventoRepository.findById(id);

        Evento ape = apeOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado."));

        EventoArtefatoResponse apeResponse = new EventoArtefatoResponse(ape);

        return new ResponseBase<>(apeResponse);
    }

    public Long cadastrar(EventoCreateRequest novo) {

        ResponseBase<ArtefatoResponse> artefatoSalvo = artefatoService.cadastrar(novo.getArtefato());

        Artefato artefato = Artefato.builder()
                .tituloArtefato(artefatoSalvo.getObjetoRetorno().getTituloArtefato())
                .descricaoArtefato(artefatoSalvo.getObjetoRetorno().getDescricaoArtefato())
                .ativo(artefatoSalvo.getObjetoRetorno().getAtivo())
                .tipoArtefato(TipoArtefato.EVENTO)
                .dataCadastro(artefatoSalvo.getObjetoRetorno().getDataCadastro())
                .idUsuario(artefatoSalvo.getObjetoRetorno().getIdUsuario())
                .build();

        Evento modeloDb = Evento.builder()
                .idArtefato(artefatoSalvo.getObjetoRetorno().getIdArtefato())
                .localizacaoEvento(novo.getLocalizacaoEvento())
                .artefato(artefato)
                .build();

        Evento apeSalvo = eventoRepository.save(modeloDb);

        return apeSalvo.getArtefato().getIdArtefato();
    }

    public EventoArtefatoResponse atualizarEvento(Long idEvento, EventoUpdateRequest eventoUpdateRequest) {

        Calendar cal = Calendar.getInstance();
        Date dataAtual = cal.getTime();

        var eventoEncontrado = eventoRepository.findById(idEvento);
        if (eventoEncontrado.isEmpty()) {
            throw new EventoNotFoundException("Evento não encontrada.");
        }

        Artefato artefato = new Artefato(eventoEncontrado.get().getArtefato());
        artefato.setTituloArtefato(eventoUpdateRequest.getTituloArtefato());
        artefato.setDescricaoArtefato(eventoUpdateRequest.getDescricaoArtefato());
        artefato.setListaImagens(eventoEncontrado.get().getArtefato().getListaImagens());
        artefato.setDataAtualizacao(dataAtual);

        var evento = eventoEncontrado.get();
        evento.setLocalizacaoEvento(eventoUpdateRequest.getLocalizacaoEvento());
        evento.setArtefato(artefato);
        eventoRepository.save(evento);

        return new EventoArtefatoResponse(
                evento.getIdArtefato(),
                evento.getLocalizacaoEvento()
        );
    }

    public ResponseEntity inativarEvento(Long idEvento) {

        var eventoEncontrado = eventoRepository.findById(idEvento);
        if (eventoEncontrado.isEmpty()) {
            throw new EventoNotFoundException("Evento não encontrada.");
        }

        Artefato artefato = new Artefato(eventoEncontrado.get().getArtefato());
        artefato.setAtivo(false);
        ArtefatoInactiveRequest artefatoInactiveRequest = new ArtefatoInactiveRequest(artefato);
        artefatoService.desativarArtefato(eventoEncontrado.get().getIdArtefato(), artefatoInactiveRequest);

        return ResponseEntity.ok().build();
    }
}