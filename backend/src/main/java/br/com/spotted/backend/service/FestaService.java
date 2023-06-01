package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Festa.FestaCreateRequest;
import br.com.spotted.backend.domain.dto.Festa.FestaResponse;
import br.com.spotted.backend.domain.dto.Festa.FestaUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Festa;
import br.com.spotted.backend.exception.ComidaNaoEncontradaException;
import br.com.spotted.backend.exception.FestaNaoEncontradaException;
import br.com.spotted.backend.repository.FestaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FestaService {

    private final FestaRepository festaRepository;
    private final UsuarioService usuarioService;

    public ResponseBase<Page<FestaResponse>> pesquisar(PaginatedSearchRequest searchRequest) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Festa> festaPage = festaRepository.findAll(searchRequest.parseToPageRequest());

        Page<FestaResponse> apeResponsePage = festaPage.map(FestaResponse::new);
        return new ResponseBase<>(apeResponsePage);
    }

    public ResponseBase<FestaResponse> pesquisarPorId(Long id) {

        Optional<Festa> apeOptional = festaRepository.findById(id);

        Festa ape = apeOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Festa não encontrada."));

        FestaResponse apeResponse = new FestaResponse(ape);

        return new ResponseBase<>(apeResponse);
    }

    public ResponseBase<FestaResponse> cadastrar(FestaCreateRequest novo) {

        Festa modeloDb = new Festa();
        modeloDb.setTituloFesta(novo.getTituloFesta());
        modeloDb.setDescricaoFesta(novo.getDescricaoFesta());
        modeloDb.setLocalizacaoFesta(novo.getLocalizacaoFesta());
        modeloDb.setIdUsuario(novo.getIdUsuario());

        usuarioService.pesquisarPorId(novo.getIdUsuario());

        //Salvando
        Festa apeSalvo = festaRepository.save(modeloDb);

        // Mapeia de entidade para dto
        FestaResponse apeResponse = new FestaResponse(apeSalvo);
        return new ResponseBase<>(apeResponse);
    }

    public FestaResponse deletar(Long idFesta) {
        var festaEncontrada = festaRepository.findById(idFesta);

        if (festaEncontrada.isEmpty()) {
            throw new FestaNaoEncontradaException("Festa não encontrada.");
        }

        var festa = festaEncontrada.get();
        festaRepository.delete(festa);

        return new FestaResponse(
                festa.getIdFesta(),
                festa.getTituloFesta(),
                festa.getDescricaoFesta(),
                festa.getLocalizacaoFesta(),
                festa.getListaImagensFesta(),
                festa.getIdUsuario()
        );
    }

    public FestaResponse atualizarFesta(Long idFesta, FestaUpdateRequest festaUpdateRequest) {

        var festaEncontrada = festaRepository.findById(idFesta);

        if (festaEncontrada.isEmpty()) {
            throw new ComidaNaoEncontradaException("Festa não encontrada.");
        }

        var festa = festaEncontrada.get();

        festa.setTituloFesta(festaUpdateRequest.getTituloFesta());
        festa.setDescricaoFesta(festaUpdateRequest.getDescricaoFesta());
        festa.setLocalizacaoFesta(festaUpdateRequest.getLocalizacaoFesta());

        var festaSalva = festaRepository.save(festa);

        return new FestaResponse(
                festa.getIdFesta(),
                festa.getTituloFesta(),
                festa.getDescricaoFesta(),
                festa.getLocalizacaoFesta(),
                festa.getListaImagensFesta(),
                festa.getIdUsuario()
        );
    }
}
