package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoInactiveRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.Objeto.ObjetoCreateRequest;
import br.com.spotted.backend.domain.dto.Objeto.ObjetoArtefatoResponse;
import br.com.spotted.backend.domain.dto.Objeto.ObjetoUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Objeto;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.TipoArtefato;
import br.com.spotted.backend.exception.ObjetoNotFoundException;
import br.com.spotted.backend.repository.ObjetoRepository;
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
public class ObjetoService {

    private final ObjetoRepository objetoRepository;
    @Autowired
    private final ArtefatoService artefatoService;

    public ResponseBase<List<ObjetoArtefatoResponse>> pesquisar() {
        Iterable<Objeto> objetos = objetoRepository.findAllObjetosWithArtefatoAtivo();
        List<ObjetoArtefatoResponse> objetoArtefatoRespons = new ArrayList<>();

        for (Objeto objeto : objetos) {
            objetoArtefatoRespons.add(new ObjetoArtefatoResponse(objeto));
        }

        return new ResponseBase<>(objetoArtefatoRespons);
    }

    public ResponseBase<Page<ObjetoArtefatoResponse>> pesquisarPaginado(PaginatedSearchRequest searchRequest) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Objeto> objetoPage = objetoRepository.findAll(searchRequest.parseToPageRequest());

        Page<ObjetoArtefatoResponse> apeResponsePage = objetoPage.map(ObjetoArtefatoResponse::new);
        return new ResponseBase<>(apeResponsePage);
    }

    public ResponseBase<ObjetoArtefatoResponse> pesquisarPorId(Long id) {

        Optional<Objeto> apeOptional = objetoRepository.findById(id);

        Objeto objeto = apeOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado."));

        ObjetoArtefatoResponse objetoArtefatoResponse = new ObjetoArtefatoResponse(objeto);

        return new ResponseBase<>(objetoArtefatoResponse);
    }

    public Long cadastrar(ObjetoCreateRequest novo) {

        ResponseBase<ArtefatoResponse> artefatoSalvo = artefatoService.cadastrar(novo.getArtefato());

        Artefato artefato = Artefato.builder()
                .tituloArtefato(artefatoSalvo.getObjetoRetorno().getTituloArtefato())
                .descricaoArtefato(artefatoSalvo.getObjetoRetorno().getDescricaoArtefato())
                .ativo(artefatoSalvo.getObjetoRetorno().getAtivo())
                .tipoArtefato(TipoArtefato.OBJETO)
                .dataCadastro(artefatoSalvo.getObjetoRetorno().getDataCadastro())
                .idUsuario(artefatoSalvo.getObjetoRetorno().getIdUsuario())
                .build();

        Objeto modeloDb = Objeto.builder()
                .idArtefato(artefatoSalvo.getObjetoRetorno().getIdArtefato())
                .localizacaoAtualObjeto(novo.getLocalizacaoAtualObjeto())
                .localizacaoAchadoObjeto(novo.getLocalizacaoAchadoObjeto())
                .artefato(artefato)
                .build();

        Objeto apeSalvo = objetoRepository.save(modeloDb);

        return apeSalvo.getArtefato().getIdArtefato();
    }

    public ObjetoArtefatoResponse atualizarObjeto(Long idObjeto, ObjetoUpdateRequest objetoUpdateRequest) {

        Calendar cal = Calendar.getInstance();
        Date dataAtual = cal.getTime();

        var objetoEncontrado = objetoRepository.findById(idObjeto);
        if (objetoEncontrado.isEmpty()) {
            throw new ObjetoNotFoundException("Objeto não encontrado.");
        }

        Artefato artefato = new Artefato(objetoEncontrado.get().getArtefato());
        artefato.setTituloArtefato(objetoUpdateRequest.getTituloArtefato());
        artefato.setDescricaoArtefato(objetoUpdateRequest.getDescricaoArtefato());
        artefato.setListaImagens(objetoEncontrado.get().getArtefato().getListaImagens());
        artefato.setDataAtualizacao(dataAtual);

        var objeto = objetoEncontrado.get();
        objeto.setLocalizacaoAchadoObjeto(objetoUpdateRequest.getLocalizacaoAchadoObjeto());
        objeto.setLocalizacaoAtualObjeto(objetoUpdateRequest.getLocalizacaoAtualObjeto());
        objeto.setArtefato(artefato);
        objetoRepository.save(objeto);

        return new ObjetoArtefatoResponse(
                objeto.getIdArtefato(),
                objeto.getLocalizacaoAchadoObjeto(),
                objeto.getLocalizacaoAtualObjeto()
        );
    }

    public ResponseEntity inativarObjeto(Long idObjeto) {

        var objetoEncontrada = objetoRepository.findById(idObjeto);
        if (objetoEncontrada.isEmpty()) {
            throw new ObjetoNotFoundException("Objeto não encontrada.");
        }

        Artefato artefato = new Artefato(objetoEncontrada.get().getArtefato());
        artefato.setAtivo(false);
        ArtefatoInactiveRequest artefatoInactiveRequest = new ArtefatoInactiveRequest(artefato);
        artefatoService.desativarArtefato(objetoEncontrada.get().getIdArtefato(), artefatoInactiveRequest);

        return ResponseEntity.ok().build();
    }
}
