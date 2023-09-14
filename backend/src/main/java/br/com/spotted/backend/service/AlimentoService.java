package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Alimento.AlimentoCreateRequest;
import br.com.spotted.backend.domain.dto.Alimento.AlimentoArtefatoResponse;
import br.com.spotted.backend.domain.dto.Alimento.AlimentoUpdateRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoInactiveRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Alimento;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.TipoArtefato;
import br.com.spotted.backend.exception.AlimentoNotFoundException;
import br.com.spotted.backend.repository.AlimentoRepository;
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
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;
    @Autowired
    private final ArtefatoService artefatoService;

    public ResponseBase<List<AlimentoArtefatoResponse>> pesquisar() {
        Iterable<Alimento> alimentos = alimentoRepository.findAllAlimentosWithArtefatoAtivo();
        List<AlimentoArtefatoResponse> alimentoArtefatoRespons = new ArrayList<>();

        for (Alimento alimento : alimentos) {
            alimentoArtefatoRespons.add(new AlimentoArtefatoResponse(alimento));
        }

        return new ResponseBase<>(alimentoArtefatoRespons);
    }

    public ResponseBase<Page<AlimentoArtefatoResponse>> pesquisarPaginado(PaginatedSearchRequest searchRequest) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Alimento> alimentoPage = alimentoRepository.findAll(searchRequest.parseToPageRequest());

        Page<AlimentoArtefatoResponse> alimentoResponsePage = alimentoPage.map(AlimentoArtefatoResponse::new);
        return new ResponseBase<>(alimentoResponsePage);
    }

    public ResponseBase<AlimentoArtefatoResponse> pesquisarPorId(Long id) {
        Optional<Alimento> alimentoOptional = alimentoRepository.findById(id);

        Alimento alimento = alimentoOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alimento não encontrada."));

        AlimentoArtefatoResponse alimentoArtefatoResponse = new AlimentoArtefatoResponse(alimento);

        return new ResponseBase<>(alimentoArtefatoResponse);
    }

    public Long cadastrar(AlimentoCreateRequest novo) {

        ResponseBase<ArtefatoResponse> artefatoSalvo = artefatoService.cadastrar(novo.getArtefato());

        Artefato artefato = Artefato.builder()
                .tituloArtefato(artefatoSalvo.getObjetoRetorno().getTituloArtefato())
                .descricaoArtefato(artefatoSalvo.getObjetoRetorno().getDescricaoArtefato())
                .ativo(artefatoSalvo.getObjetoRetorno().getAtivo())
                .tipoArtefato(TipoArtefato.ALIMENTO)
                .dataCadastro(artefatoSalvo.getObjetoRetorno().getDataCadastro())
                .idUsuario(artefatoSalvo.getObjetoRetorno().getIdUsuario())
                .build();

        Alimento modeloDb = Alimento.builder()
                .idArtefato(artefatoSalvo.getObjetoRetorno().getIdArtefato())
                .tipoAlimento(novo.getTipoAlimento().toString())
                .marcaAlimento(novo.getMarcaAlimento())
                .saborAlimento(novo.getSaborAlimento())
                .unidadeAlimento(novo.getUnidadeAlimento())
                .precoAlimento(novo.getPrecoAlimento())
                .ofertaAlimento(novo.getOfertaAlimento())
                .artefato(artefato)
                .build();

        Alimento alimentoSalvo = alimentoRepository.save(modeloDb);

        return alimentoSalvo.getArtefato().getIdArtefato();
    }

    public AlimentoArtefatoResponse atualizarAlimento(Long idAlimento, AlimentoUpdateRequest alimentoUpdateRequest){

        Calendar cal = Calendar.getInstance();
        Date dataAtual = cal.getTime();

        var alimentoEncontrada = alimentoRepository.findById(idAlimento);
        if(alimentoEncontrada.isEmpty()){
            throw new AlimentoNotFoundException("Alimento não encontrada.");
        }

        Artefato artefato = new Artefato(alimentoEncontrada.get().getArtefato());
        artefato.setTituloArtefato(alimentoUpdateRequest.getTituloArtefato());
        artefato.setDescricaoArtefato(alimentoUpdateRequest.getDescricaoArtefato());
        artefato.setListaImagens(alimentoEncontrada.get().getArtefato().getListaImagens());
        artefato.setDataAtualizacao(dataAtual);

        var alimento = alimentoEncontrada.get();
        alimento.setTipoAlimento(alimentoUpdateRequest.getTipoAlimento().toString());
        alimento.setMarcaAlimento(alimentoUpdateRequest.getMarcaAlimento());
        alimento.setSaborAlimento(alimentoUpdateRequest.getSaborAlimento());
        alimento.setUnidadeAlimento(alimentoUpdateRequest.getUnidadeAlimento());
        alimento.setPrecoAlimento(alimentoUpdateRequest.getPrecoAlimento());
        alimento.setOfertaAlimento(alimentoUpdateRequest.getOfertaAlimento());
        alimento.setArtefato(artefato);
        alimentoRepository.save(alimento);

        return new AlimentoArtefatoResponse(
                alimento.getIdArtefato(),
                alimento.getTipoAlimento(),
                alimento.getMarcaAlimento(),
                alimento.getSaborAlimento(),
                alimento.getUnidadeAlimento(),
                alimento.getPrecoAlimento(),
                alimento.getOfertaAlimento()
        );
    }

    public ResponseEntity inativarAlimento(Long idAlimento) {

        var alimentoEncontrada = alimentoRepository.findById(idAlimento);
        if (alimentoEncontrada.isEmpty()) {
            throw new AlimentoNotFoundException("Alimento não encontrada.");
        }

        Artefato artefato = new Artefato(alimentoEncontrada.get().getArtefato());
        artefato.setAtivo(false);
        ArtefatoInactiveRequest artefatoInactiveRequest = new ArtefatoInactiveRequest(artefato);
        artefatoService.desativarArtefato(alimentoEncontrada.get().getIdArtefato(), artefatoInactiveRequest);

        return ResponseEntity.ok().build();
    }
}