package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Moradia.MoradiaResponse;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Moradia;
import br.com.spotted.backend.repository.MoradiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MoradiaService {

    private final MoradiaRepository moradiaRepository;
    @Autowired
    private final ArtefatoService artefatoService;

    public ResponseBase<Page<MoradiaResponse>> pesquisar(PaginatedSearchRequest searchRequest) {

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

//    public ResponseBase<MoradiaResponse> cadastrar(MoradiaCreateRequest novo) {
//
//        //Cadastrando o artefato
//        ResponseBase<ArtefatoResponse> idArtefato = artefatoService.cadastrar(novo.getArtefato());
//
//        //Cadastrando o Alimento
//        Artefato artefato = new Artefato();
//        artefato.setIdArtefato(idArtefato.getObjetoRetorno().getIdArtefato());
//        artefato.setTituloArtefato(idArtefato.getObjetoRetorno().getTituloArtefato());
//        artefato.setDescricaoArtefato(idArtefato.getObjetoRetorno().getDescricaoArtefato());
//        artefato.setTipoArtefato(idArtefato.getObjetoRetorno().getTipoArtefato());
//        artefato.setAtivo(idArtefato.getObjetoRetorno().getAtivo());
//        artefato.setDataCadastro(idArtefato.getObjetoRetorno().getDataCadastro());
//        artefato.setIdUsuario(idArtefato.getObjetoRetorno().getIdUsuario());
//
//        Moradia modeloDb = new Moradia();
//        modeloDb.setLocalizacaoMoradia(novo.getLocalizacaoMoradia());
//        modeloDb.setQtdMoradoresAtuaisMoradia(novo.getQtdMoradoresAtuaisMoradia());
//        modeloDb.setQtdMoradoresPermitidoMoradia(novo.getQtdMoradoresPermitidoMoradia());
//        modeloDb.setPrecoAluguelTotalMoradia(novo.getPrecoAluguelTotalMoradia());
//        modeloDb.setPrecoAluguelPorPessoaMoradia(novo.getPrecoAluguelPorPessoaMoradia());
//        modeloDb.setVagaGaragemMoradia(novo.getVagaGaragemMoradia());
//        modeloDb.setAnimaisEstimacaoMoradia(novo.getAnimaisEstimacaoMoradia());
//        modeloDb.setArtefato(artefato);
//
//        //Salvando
//        Moradia moradiaSalvo = moradiaRepository.save(modeloDb);
//
//        // Mmoradiaia de entidade para dto
//        MoradiaResponse moradiaResponse = new MoradiaResponse(moradiaSalvo);
//        return new ResponseBase<>(moradiaResponse);
//    }

//    public MoradiaResponse atualizarMoradia(Long idMoradia, MoradiaUpdateRequest moradiaUpdateRequest) {
//
//        var moradiaEncontrado = moradiaRepository.findById(idMoradia);
//
//        if (moradiaEncontrado.isEmpty()) {
//            throw new MoradiaNotFoundException("Moradia não encontrado.");
//        }
//
//        var moradia = moradiaEncontrado.get();
//
//        moradia.setTituloMoradia(moradiaUpdateRequest.getTituloMoradia());
//        moradia.setDescricaoMoradia(moradiaUpdateRequest.getDescricaoMoradia());
//        moradia.setQtdMoradoresAtuaisMoradia(moradiaUpdateRequest.getQtdMoradoresAtuaisMoradia());
//        moradia.setQtdMoradoresPermitidoMoradia(moradiaUpdateRequest.getQtdMoradoresPermitidoMoradia());
//        moradia.setPrecoAluguelTotalMoradia(moradiaUpdateRequest.getPrecoAluguelTotalMoradia());
//        moradia.setPrecoAluguelPorPessoaMoradia(moradiaUpdateRequest.getPrecoAluguelPorPessoaMoradia());
//        moradia.setVagaGaragemMoradia(moradiaUpdateRequest.getVagaGaragemMoradia());
//        moradia.setAnimaisEstimacaoMoradia(moradiaUpdateRequest.getAnimaisEstimacaoMoradia());
//
//        var moradiaSalvo = moradiaRepository.save(moradia);
//
//        return new MoradiaResponse(
//                moradia.getIdMoradia(),
//                moradia.getTituloMoradia(),
//                moradia.getDescricaoMoradia(),
//                moradia.getLocalizacaoMoradia(),
//                moradia.getQtdMoradoresAtuaisMoradia(),
//                moradia.getQtdMoradoresPermitidoMoradia(),
//                moradia.getPrecoAluguelTotalMoradia(),
//                moradia.getPrecoAluguelPorPessoaMoradia(),
//                moradia.getVagaGaragemMoradia(),
//                moradia.getAnimaisEstimacaoMoradia(),
//                moradia.getListaImagensMoradia(),
//                moradia.getIdUsuario()
//        );
//    }

//    public MoradiaResponse deletar(Long idMoradia) {
//        var moradiaEncontrado = moradiaRepository.findById(idMoradia);
//
//        if (moradiaEncontrado.isEmpty()) {
//            throw new MoradiaNotFoundException("Apê não encontrado.");
//        }
//
//        var moradia = moradiaEncontrado.get();
//        moradiaRepository.delete(moradia);
//
//        return new MoradiaResponse(
//                moradia.getIdMoradia(),
//                moradia.getTituloMoradia(),
//                moradia.getDescricaoMoradia(),
//                moradia.getLocalizacaoMoradia(),
//                moradia.getQtdMoradoresAtuaisMoradia(),
//                moradia.getQtdMoradoresPermitidoMoradia(),
//                moradia.getPrecoAluguelTotalMoradia(),
//                moradia.getPrecoAluguelPorPessoaMoradia(),
//                moradia.getVagaGaragemMoradia(),
//                moradia.getAnimaisEstimacaoMoradia(),
//                moradia.getListaImagensMoradia(),
//                moradia.getIdUsuario()
//        );
//    }
}
