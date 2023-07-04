package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.Festa.FestaCreateRequest;
import br.com.spotted.backend.domain.dto.Festa.FestaResponse;
import br.com.spotted.backend.domain.dto.Festa.FestaUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.Festa;
import br.com.spotted.backend.exception.AlimentoNaoEncontradoException;
import br.com.spotted.backend.exception.FestaNaoEncontradaException;
import br.com.spotted.backend.repository.FestaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FestaService {

    private final FestaRepository festaRepository;
    @Autowired
    private final ArtefatoService artefatoService;

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

        //Cadastrando o artefato
        ResponseBase<ArtefatoResponse> idArtefato = artefatoService.cadastrar(novo.getArtefato());

        //Cadastrando o Alimento
        Artefato artefato = new Artefato();
        artefato.setIdArtefato(idArtefato.getObjetoRetorno().getIdArtefato());
        artefato.setTituloArtefato(idArtefato.getObjetoRetorno().getTituloArtefato());
        artefato.setDescricaoArtefato(idArtefato.getObjetoRetorno().getDescricaoArtefato());
        artefato.setTipoArtefato(idArtefato.getObjetoRetorno().getTipoArtefato());
        artefato.setAtivo(idArtefato.getObjetoRetorno().getAtivo());
        artefato.setDataCadastro(idArtefato.getObjetoRetorno().getDataCadastro());
        artefato.setIdUsuario(idArtefato.getObjetoRetorno().getIdUsuario());

        Festa modeloDb = new Festa();
        modeloDb.setIdArtefato(idArtefato.getObjetoRetorno().getIdArtefato());
        modeloDb.setLocalizacaoFesta(novo.getLocalizacaoFesta());
        modeloDb.setArtefato(artefato);

        //Salvando
        Festa apeSalvo = festaRepository.save(modeloDb);

        // Mapeia de entidade para dto
        FestaResponse apeResponse = new FestaResponse(apeSalvo);
        return new ResponseBase<>(apeResponse);
    }

//    public FestaResponse atualizarFesta(Long idFesta, FestaUpdateRequest festaUpdateRequest) {
//
//        var festaEncontrada = festaRepository.findById(idFesta);
//
//        if (festaEncontrada.isEmpty()) {
//            throw new FestaNaoEncontradaException("Festa não encontrada.");
//        }
//
//        var festa = festaEncontrada.get();
//
//        festa.setTituloFesta(festaUpdateRequest.getTituloFesta());
//        festa.setDescricaoFesta(festaUpdateRequest.getDescricaoFesta());
//        festa.setLocalizacaoFesta(festaUpdateRequest.getLocalizacaoFesta());
//
//        var festaSalva = festaRepository.save(festa);
//
//        return new FestaResponse(
//                festa.getIdFesta(),
//                festa.getTituloFesta(),
//                festa.getDescricaoFesta(),
//                festa.getLocalizacaoFesta(),
//                festa.getListaImagensFesta(),
//                festa.getIdUsuario()
//        );
//    }

//    public FestaResponse deletar(Long idFesta) {
//        var festaEncontrada = festaRepository.findById(idFesta);
//
//        if (festaEncontrada.isEmpty()) {
//            throw new FestaNaoEncontradaException("Festa não encontrada.");
//        }
//
//        var festa = festaEncontrada.get();
//        festaRepository.delete(festa);
//
//        return new FestaResponse(
//                festa.getIdFesta(),
//                festa.getTituloFesta(),
//                festa.getDescricaoFesta(),
//                festa.getLocalizacaoFesta(),
//                festa.getListaImagensFesta(),
//                festa.getIdUsuario()
//        );
//    }
}
