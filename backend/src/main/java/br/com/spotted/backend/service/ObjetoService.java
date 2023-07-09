package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoInactiveRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.Objeto.ObjetoCreateRequest;
import br.com.spotted.backend.domain.dto.Objeto.ObjetoResponse;
import br.com.spotted.backend.domain.dto.Objeto.ObjetoUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Objeto;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.exception.ObjetoNotFoundException;
import br.com.spotted.backend.repository.ObjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;
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

    public ResponseBase<ObjetoResponse> cadastrar(ObjetoCreateRequest novo) {

        ResponseBase<ArtefatoResponse> artefatoSalvo = artefatoService.cadastrar(novo.getArtefato());

        Artefato artefato = Artefato.builder()
                .tituloArtefato(artefatoSalvo.getObjetoRetorno().getTituloArtefato())
                .descricaoArtefato(artefatoSalvo.getObjetoRetorno().getDescricaoArtefato())
                .tipoArtefato(artefatoSalvo.getObjetoRetorno().getTipoArtefato())
                .ativo(artefatoSalvo.getObjetoRetorno().getAtivo())
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

        ObjetoResponse apeResponse = new ObjetoResponse(apeSalvo);
        return new ResponseBase<>(apeResponse);
    }

    public ObjetoResponse atualizarObjeto(Long idObjeto, ObjetoUpdateRequest objetoUpdateRequest) {

        Calendar cal = Calendar.getInstance();
        Date dataAtual = cal.getTime();

        var objetoEncontrado = objetoRepository.findById(idObjeto);
        if (objetoEncontrado.isEmpty()) {
            throw new ObjetoNotFoundException("Objeto não encontrado.");
        }

        Artefato artefato = new Artefato(objetoEncontrado.get().getArtefato());
        artefato.setTituloArtefato(objetoUpdateRequest.getTituloArtefato());
        artefato.setDescricaoArtefato(objetoUpdateRequest.getDescricaoArtefato());
        artefato.setDataAtualizacao(dataAtual);

        var objeto = objetoEncontrado.get();
        objeto.setLocalizacaoAchadoObjeto(objetoUpdateRequest.getLocalizacaoAchadoObjeto());
        objeto.setLocalizacaoAtualObjeto(objetoUpdateRequest.getLocalizacaoAtualObjeto());
        objeto.setArtefato(artefato);
        objetoRepository.save(objeto);

        return new ObjetoResponse(
                objeto.getIdArtefato(),
                objeto.getLocalizacaoAchadoObjeto(),
                objeto.getLocalizacaoAtualObjeto(),
                objeto.getArtefato().getTituloArtefato(),
                objeto.getArtefato().getDescricaoArtefato()
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
