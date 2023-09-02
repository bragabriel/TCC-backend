package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Alimento.AlimentoResponse;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoInactiveRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.Festa.FestaCreateRequest;
import br.com.spotted.backend.domain.dto.Festa.FestaResponse;
import br.com.spotted.backend.domain.dto.Festa.FestaUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Alimento;
import br.com.spotted.backend.domain.entity.Festa;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.TipoArtefato;
import br.com.spotted.backend.exception.FestaNotFoundException;
import br.com.spotted.backend.repository.FestaRepository;
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
public class FestaService {

    private final FestaRepository festaRepository;
    @Autowired
    private final ArtefatoService artefatoService;

    public ResponseBase<List<FestaResponse>> pesquisar() {
        Iterable<Festa> festas = festaRepository.findAllFestasWithArtefatoAtivo();
        List<FestaResponse> festaResponse = new ArrayList<>();

        for (Festa festa : festas) {
            festaResponse.add(new FestaResponse(festa));
        }

        return new ResponseBase<>(festaResponse);
    }

    public ResponseBase<Page<FestaResponse>> pesquisarPaginado(PaginatedSearchRequest searchRequest) {

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

    public Long cadastrar(FestaCreateRequest novo) {

        ResponseBase<ArtefatoResponse> artefatoSalvo = artefatoService.cadastrar(novo.getArtefato());

        Artefato artefato = Artefato.builder()
                .tituloArtefato(artefatoSalvo.getObjetoRetorno().getTituloArtefato())
                .descricaoArtefato(artefatoSalvo.getObjetoRetorno().getDescricaoArtefato())
                .ativo(artefatoSalvo.getObjetoRetorno().getAtivo())
                .tipoArtefato(TipoArtefato.FESTA)
                .dataCadastro(artefatoSalvo.getObjetoRetorno().getDataCadastro())
                .idUsuario(artefatoSalvo.getObjetoRetorno().getIdUsuario())
                .build();

        Festa modeloDb = Festa.builder()
                .idArtefato(artefatoSalvo.getObjetoRetorno().getIdArtefato())
                .localizacaoFesta(novo.getLocalizacaoFesta())
                .artefato(artefato)
                .build();

        Festa apeSalvo = festaRepository.save(modeloDb);

        return apeSalvo.getArtefato().getIdArtefato();
    }

    public FestaResponse atualizarFesta(Long idFesta, FestaUpdateRequest festaUpdateRequest) {

        Calendar cal = Calendar.getInstance();
        Date dataAtual = cal.getTime();

        var festaEncontrada = festaRepository.findById(idFesta);
        if (festaEncontrada.isEmpty()) {
            throw new FestaNotFoundException("Festa não encontrada.");
        }

        Artefato artefato = new Artefato(festaEncontrada.get().getArtefato());
        artefato.setTituloArtefato(festaUpdateRequest.getTituloArtefato());
        artefato.setDescricaoArtefato(festaUpdateRequest.getDescricaoArtefato());
        artefato.setDataAtualizacao(dataAtual);

        var festa = festaEncontrada.get();
        festa.setLocalizacaoFesta(festaUpdateRequest.getLocalizacaoFesta());
        festa.setArtefato(artefato);
        festaRepository.save(festa);

        return new FestaResponse(
                festa.getIdArtefato(),
                festa.getLocalizacaoFesta()
        );
    }

    public ResponseEntity inativarFesta(Long idFesta) {

        var festaEncontrada = festaRepository.findById(idFesta);
        if (festaEncontrada.isEmpty()) {
            throw new FestaNotFoundException("Festa não encontrada.");
        }

        Artefato artefato = new Artefato(festaEncontrada.get().getArtefato());
        artefato.setAtivo(false);
        ArtefatoInactiveRequest artefatoInactiveRequest = new ArtefatoInactiveRequest(artefato);
        artefatoService.desativarArtefato(festaEncontrada.get().getIdArtefato(), artefatoInactiveRequest);

        return ResponseEntity.ok().build();
    }
}