package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Objeto.ObjetoResponse;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Objeto;
import br.com.spotted.backend.repository.ObjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObjetoService {

    private final ObjetoRepository objetoRepository;
    @Autowired
    private final ArtefatoService artefatoService;
    public ResponseBase<Page<ObjetoResponse>> pesquisar(PaginatedSearchRequest searchRequest) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Objeto> objetoPage = objetoRepository.findAll(searchRequest.parseToPageRequest());

        Page<ObjetoResponse> apeResponsePage = objetoPage.map(ObjetoResponse::new);
        return new ResponseBase<>(apeResponsePage);
    }

    public ResponseBase<ObjetoResponse> pesquisarPorId(Long id) {

        Optional<Objeto> apeOptional = objetoRepository.findById(id);

        Objeto objeto = apeOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado."));

        ObjetoResponse objetoResponse = new ObjetoResponse(objeto);

        return new ResponseBase<>(objetoResponse);
    }

//    public ResponseBase<ObjetoResponse> cadastrar(ObjetoCreateRequest novo) {
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
//        Objeto modeloDb = new Objeto();
//        modeloDb.setLocalizacaoAchadoObjeto(novo.getLocalizacaoAchadoObjeto());
//        modeloDb.setLocalizacaoAtualObjeto(novo.getLocalizacaoAtualObjeto());
//        modeloDb.setArtefato(artefato);
//
//        //Salvando
//        Objeto apeSalvo = objetoRepository.save(modeloDb);
//
//        // Mapeia de entidade para dto
//        ObjetoResponse apeResponse = new ObjetoResponse(apeSalvo);
//        return new ResponseBase<>(apeResponse);
//    }

//    public ObjetoResponse atualizarObjeto(Long idObjeto, ObjetoUpdateRequest objetoUpdateRequest) {
//
//        var objetoEncontrado = objetoRepository.findById(idObjeto);
//
//        if (objetoEncontrado.isEmpty()) {
//            throw new ObjetoNotFoundException("Objeto não encontrado.");
//        }
//
//        var objeto = objetoEncontrado.get();
//
//        objeto.setTituloObjeto(objetoUpdateRequest.getTituloObjeto());
//        objeto.setDescricaoObjeto(objetoUpdateRequest.getDescricaoObjeto());
//        objeto.setLocalizacaoAchadoObjeto(objetoUpdateRequest.getLocalizacaoAchadoObjeto());
//        objeto.setLocalizacaoAtualObjeto(objetoUpdateRequest.getLocalizacaoAtualObjeto());
//
//        var objetoSalvo = objetoRepository.save(objeto);
//
//        return new ObjetoResponse(
//                objeto.getIdObjeto(),
//                objeto.getTituloObjeto(),
//                objeto.getDescricaoObjeto(),
//                objeto.getLocalizacaoAchadoObjeto(),
//                objeto.getLocalizacaoAtualObjeto(),
//                objeto.getListaImagensObjeto(),
//                objeto.getIdUsuario()
//        );
//    }

//    public ObjetoResponse deletar(Long idObjeto) {
//        var objetoEncontrado = objetoRepository.findById(idObjeto);
//
//        if (objetoEncontrado.isEmpty()) {
//            throw new ObjetoNotFoundException("Objeto não encontrado.");
//        }
//
//        var objeto = objetoEncontrado.get();
//        objetoRepository.delete(objeto);
//
//        return new ObjetoResponse(
//                objeto.getIdObjeto(),
//                objeto.getTituloObjeto(),
//                objeto.getDescricaoObjeto(),
//                objeto.getLocalizacaoAchadoObjeto(),
//                objeto.getLocalizacaoAtualObjeto(),
//                objeto.getListaImagensObjeto(),
//                objeto.getIdUsuario()
//        );
//    }
}
