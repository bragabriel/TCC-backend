package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Ape.ApeCreateRequest;
import br.com.spotted.backend.domain.dto.Ape.ApeResponse;
import br.com.spotted.backend.domain.dto.Ape.ApeUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Ape;
import br.com.spotted.backend.exception.ApeNaoEncontradoException;
import br.com.spotted.backend.exception.ComidaNaoEncontradaException;
import br.com.spotted.backend.repository.ApeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApeService {

    private final ApeRepository apeRepository;
    private final UsuarioService usuarioService;

    public ResponseBase<Page<ApeResponse>> pesquisar(PaginatedSearchRequest searchRequest) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Ape> apePage = apeRepository.findAll(searchRequest.parseToPageRequest());

        Page<ApeResponse> apeResponsePage = apePage.map(ApeResponse::new);
        return new ResponseBase<>(apeResponsePage);
    }

    public ResponseBase<ApeResponse> pesquisarPorId(Long id) {

        Optional<Ape> apeOptional = apeRepository.findById(id);

        Ape ape = apeOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Apê não encontrado."));

        ApeResponse apeResponse = new ApeResponse(ape);

        return new ResponseBase<>(apeResponse);
    }

    public ResponseBase<ApeResponse> cadastrar(ApeCreateRequest novo) {

        Ape modeloDb = new Ape();
        modeloDb.setTituloApe(novo.getTituloApe());
        modeloDb.setDescricaoApe(novo.getDescricaoApe());
        modeloDb.setLocalizacaoApe(novo.getLocalizacaoApe());
        modeloDb.setQtdMoradoresAtuaisApe(novo.getQtdMoradoresAtuaisApe());
        modeloDb.setQtdMoradoresPermitidoApe(novo.getQtdMoradoresPermitidoApe());
        modeloDb.setPrecoAluguelTotalApe(novo.getPrecoAluguelTotalApe());
        modeloDb.setPrecoAluguelPorPessoaApe(novo.getPrecoAluguelPorPessoaApe());
        modeloDb.setVagaGaragemApe(novo.getVagaGaragemApe());
        modeloDb.setAnimaisEstimacaoApe(novo.getAnimaisEstimacaoApe());
        modeloDb.setImagemApe(novo.getImagemApe());
        modeloDb.setIdUsuario(novo.getIdUsuario());

        usuarioService.pesquisarPorId(novo.getIdUsuario());

        //Salvando
        Ape apeSalvo = apeRepository.save(modeloDb);

        // Mapeia de entidade para dto
        ApeResponse apeResponse = new ApeResponse(apeSalvo);
        return new ResponseBase<>(apeResponse);
    }

    public ApeResponse deletar(Long idApe) {
        var apeEncontrado = apeRepository.findById(idApe);

        if (apeEncontrado.isEmpty()) {
            throw new ApeNaoEncontradoException("Apê não encontrado.");
        }

        var ape = apeEncontrado.get();
        apeRepository.delete(ape);

        return new ApeResponse(
            ape.getIdApe(),
            ape.getTituloApe(),
            ape.getDescricaoApe(),
            ape.getLocalizacaoApe(),
            ape.getQtdMoradoresAtuaisApe(),
            ape.getQtdMoradoresPermitidoApe(),
            ape.getPrecoAluguelTotalApe(),
            ape.getPrecoAluguelPorPessoaApe(),
            ape.getVagaGaragemApe(),
            ape.getAnimaisEstimacaoApe(),
            ape.getImagemApe(),
            ape.getIdUsuario()
        );
    }

    public ApeResponse atualizarApe(Long idApe, ApeUpdateRequest apeUpdateRequest) {

        var apeEncontrado = apeRepository.findById(idApe);

        if (apeEncontrado.isEmpty()) {
            throw new ComidaNaoEncontradaException("Ape não encontrado.");
        }

        var ape = apeEncontrado.get();

        ape.setTituloApe(apeUpdateRequest.getTituloApe());
        ape.setDescricaoApe(apeUpdateRequest.getDescricaoApe());
        ape.setQtdMoradoresAtuaisApe(apeUpdateRequest.getQtdMoradoresAtuaisApe());
        ape.setQtdMoradoresPermitidoApe(apeUpdateRequest.getQtdMoradoresPermitidoApe());
        ape.setPrecoAluguelTotalApe(apeUpdateRequest.getPrecoAluguelTotalApe());
        ape.setPrecoAluguelPorPessoaApe(apeUpdateRequest.getPrecoAluguelPorPessoaApe());
        ape.setVagaGaragemApe(apeUpdateRequest.getVagaGaragemApe());
        ape.setAnimaisEstimacaoApe(apeUpdateRequest.getAnimaisEstimacaoApe());
        ape.setImagemApe(apeUpdateRequest.getImagemComida());

        var apeSalvo = apeRepository.save(ape);

        return new ApeResponse(
                ape.getIdApe(),
                ape.getTituloApe(),
                ape.getDescricaoApe(),
                ape.getLocalizacaoApe(),
                ape.getQtdMoradoresAtuaisApe(),
                ape.getQtdMoradoresPermitidoApe(),
                ape.getPrecoAluguelTotalApe(),
                ape.getPrecoAluguelPorPessoaApe(),
                ape.getVagaGaragemApe(),
                ape.getAnimaisEstimacaoApe(),
                ape.getImagemApe(),
                ape.getIdUsuario()
        );
    }
}
