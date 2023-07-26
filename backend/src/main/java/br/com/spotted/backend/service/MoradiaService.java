package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoInactiveRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.Moradia.MoradiaCreateRequest;
import br.com.spotted.backend.domain.dto.Moradia.MoradiaResponse;
import br.com.spotted.backend.domain.dto.Moradia.MoradiaUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Moradia;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.exception.MoradiaNotFoundException;
import br.com.spotted.backend.repository.MoradiaRepository;
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
public class MoradiaService {

    private final MoradiaRepository moradiaRepository;
    @Autowired
    private final ArtefatoService artefatoService;

    public ResponseBase<List<MoradiaResponse>> pesquisar() {
        Iterable<Moradia> moradias = moradiaRepository.findAll();
        List<MoradiaResponse> moradiaResponses = new ArrayList<>();

        for (Moradia moradia : moradias) {
            moradiaResponses.add(new MoradiaResponse(moradia));
        }

        return new ResponseBase<>(moradiaResponses);
    }

    public ResponseBase<Page<MoradiaResponse>> pesquisarPaginado(PaginatedSearchRequest searchRequest) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Moradia> moradiaPage = moradiaRepository.findAll(searchRequest.parseToPageRequest());

        Page<MoradiaResponse> moradiaResponsePage = moradiaPage.map(MoradiaResponse::new);
        return new ResponseBase<>(moradiaResponsePage);
    }

    public ResponseBase<MoradiaResponse> pesquisarPorId(Long id) {

        Optional<Moradia> moradiaOptional = moradiaRepository.findById(id);

        Moradia moradia = moradiaOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Apê não encontrado."));

        MoradiaResponse moradiaResponse = new MoradiaResponse(moradia);

        return new ResponseBase<>(moradiaResponse);
    }

    public ResponseBase<MoradiaResponse> cadastrar(MoradiaCreateRequest novo) {

        ResponseBase<ArtefatoResponse> artefatoSalvo = artefatoService.cadastrar(novo.getArtefato());

        Artefato artefato = Artefato.builder()
                .tituloArtefato(artefatoSalvo.getObjetoRetorno().getTituloArtefato())
                .descricaoArtefato(artefatoSalvo.getObjetoRetorno().getDescricaoArtefato())
                .tipoArtefato(artefatoSalvo.getObjetoRetorno().getTipoArtefato())
                .ativo(artefatoSalvo.getObjetoRetorno().getAtivo())
                .dataCadastro(artefatoSalvo.getObjetoRetorno().getDataCadastro())
                .idUsuario(artefatoSalvo.getObjetoRetorno().getIdUsuario())
                .build();

        Moradia modeloDb = Moradia.builder()
                .idArtefato(artefatoSalvo.getObjetoRetorno().getIdArtefato())
                .estadoMoradia(novo.getEstadoMoradia())
                .cidadeMoradia(novo.getCidadeMoradia())
                .bairroMoradia(novo.getBairroMoradia())
                .cepMoradia(novo.getCepMoradia())
                .qtdMoradoresAtuaisMoradia(novo.getQtdMoradoresAtuaisMoradia())
                .qtdMoradoresPermitidoMoradia(novo.getQtdMoradoresPermitidoMoradia())
                .precoAluguelTotalMoradia(novo.getPrecoAluguelTotalMoradia())
                .precoAluguelPorPessoaMoradia(novo.getPrecoAluguelPorPessoaMoradia())
                .vagaGaragemMoradia(novo.getVagaGaragemMoradia())
                .animaisEstimacaoMoradia(novo.getAnimaisEstimacaoMoradia())
                .artefato(artefato)
                .build();

        Moradia moradiaSalvo = moradiaRepository.save(modeloDb);

        MoradiaResponse moradiaResponse = new MoradiaResponse(moradiaSalvo);
        return new ResponseBase<>(moradiaResponse);
    }

    public MoradiaResponse atualizarMoradia(Long idMoradia, MoradiaUpdateRequest moradiaUpdateRequest) {

        Calendar cal = Calendar.getInstance();
        Date dataAtual = cal.getTime();

        var moradiaEncontrado = moradiaRepository.findById(idMoradia);
        if (moradiaEncontrado.isEmpty()) {
            throw new MoradiaNotFoundException("Moradia não encontrado.");
        }

        Artefato artefato = new Artefato(moradiaEncontrado.get().getArtefato());
        artefato.setTituloArtefato(moradiaUpdateRequest.getTituloArtefato());
        artefato.setDescricaoArtefato(moradiaUpdateRequest.getDescricaoArtefato());
        artefato.setDataAtualizacao(dataAtual);

        var moradia = moradiaEncontrado.get();
        moradia.setQtdMoradoresAtuaisMoradia(moradiaUpdateRequest.getQtdMoradoresAtuaisMoradia());
        moradia.setQtdMoradoresPermitidoMoradia(moradiaUpdateRequest.getQtdMoradoresPermitidoMoradia());
        moradia.setPrecoAluguelTotalMoradia(moradiaUpdateRequest.getPrecoAluguelTotalMoradia());
        moradia.setPrecoAluguelPorPessoaMoradia(moradiaUpdateRequest.getPrecoAluguelPorPessoaMoradia());
        moradia.setVagaGaragemMoradia(moradiaUpdateRequest.getVagaGaragemMoradia());
        moradia.setAnimaisEstimacaoMoradia(moradiaUpdateRequest.getAnimaisEstimacaoMoradia());
        moradia.setEstadoMoradia(moradiaUpdateRequest.getEstadoMoradia());
        moradia.setCidadeMoradia(moradiaUpdateRequest.getCidadeMoradia());
        moradia.setBairroMoradia(moradiaUpdateRequest.getBairroMoradia());
        moradia.setCepMoradia(moradiaUpdateRequest.getCepMoradia());
        moradia.setArtefato(artefato);
        moradiaRepository.save(moradia);

        return new MoradiaResponse(
                moradia.getIdArtefato(),
                moradia.getEstadoMoradia(),
                moradia.getCidadeMoradia(),
                moradia.getBairroMoradia(),
                moradia.getCepMoradia(),
                moradia.getQtdMoradoresAtuaisMoradia(),
                moradia.getQtdMoradoresPermitidoMoradia(),
                moradia.getPrecoAluguelTotalMoradia(),
                moradia.getPrecoAluguelPorPessoaMoradia(),
                moradia.getVagaGaragemMoradia(),
                moradia.getAnimaisEstimacaoMoradia()
        );
    }

    public ResponseEntity inativarMoradia(Long idMoradia) {

        var moradiaEncontrada = moradiaRepository.findById(idMoradia);
        if (moradiaEncontrada.isEmpty()) {
            throw new MoradiaNotFoundException("Moradia não encontrada.");
        }

        Artefato artefato = new Artefato(moradiaEncontrada.get().getArtefato());
        artefato.setAtivo(false);
        ArtefatoInactiveRequest artefatoInactiveRequest = new ArtefatoInactiveRequest(artefato);
        artefatoService.desativarArtefato(moradiaEncontrada.get().getIdArtefato(), artefatoInactiveRequest);

        return ResponseEntity.ok().build();
    }
}
