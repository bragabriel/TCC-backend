package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Alimento.AlimentoCreateRequest;
import br.com.spotted.backend.domain.dto.Alimento.AlimentoResponse;
import br.com.spotted.backend.domain.dto.Alimento.AlimentoUpdateRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Alimento;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.exception.AlimentoNaoEncontradoException;
import br.com.spotted.backend.repository.AlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;
    @Autowired
    private final ArtefatoService artefatoService;


    public ResponseBase<Page<AlimentoResponse>> pesquisar(PaginatedSearchRequest searchRequest) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Alimento> alimentoPage = alimentoRepository.findAll(searchRequest.parseToPageRequest());

        Page<AlimentoResponse> alimentoResponsePage = alimentoPage.map(AlimentoResponse::new);
        return new ResponseBase<>(alimentoResponsePage);
    }

    public ResponseBase<AlimentoResponse> pesquisarPorId(Long id) {
        Optional<Alimento> alimentoOptional = alimentoRepository.findById(id);

        Alimento alimento = alimentoOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alimento não encontrada."));

        AlimentoResponse alimentoResponse = new AlimentoResponse(alimento);

        return new ResponseBase<>(alimentoResponse);
    }

    public ResponseBase<AlimentoResponse> cadastrar(AlimentoCreateRequest novo) {

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

        Alimento modeloDb = new Alimento();
        modeloDb.setIdArtefato(idArtefato.getObjetoRetorno().getIdArtefato());
        modeloDb.setTipoAlimento(novo.getTipoAlimento());
        modeloDb.setMarcaAlimento(novo.getMarcaAlimento());
        modeloDb.setSaborAlimento(novo.getSaborAlimento());
        modeloDb.setUnidadeAlimento(novo.getUnidadeAlimento());
        modeloDb.setPrecoAlimento(novo.getPrecoAlimento());
        modeloDb.setOfertaAlimento(novo.getOfertaAlimento());
        modeloDb.setArtefato(artefato);

        //Salvando
        Alimento alimentoSalvo = alimentoRepository.save(modeloDb);

        // Mapeia de entidade para dto
        AlimentoResponse alimentoResponse = new AlimentoResponse(alimentoSalvo);
        return new ResponseBase<>(alimentoResponse);
    }

    public AlimentoResponse deletar(Long idAlimento) {
        var alimentoEncontrada = alimentoRepository.findById(idAlimento);

        if (alimentoEncontrada.isEmpty()) {
            throw new AlimentoNaoEncontradoException("Alimento não encontrada.");
        }

        var alimento = alimentoEncontrada.get();
        alimentoRepository.delete(alimento);

        return new AlimentoResponse(
                alimento.getIdArtefato(),
                alimento.getTipoAlimento(),
                alimento.getMarcaAlimento(),
                alimento.getSaborAlimento(),
                alimento.getUnidadeAlimento(),
                alimento.getPrecoAlimento(),
                alimento.getOfertaAlimento(),
                alimento.getArtefato().getDescricaoArtefato()
                //alimento.getListaImagensAlimento
        );
    }

    public AlimentoResponse atualizarAlimento(Long idAlimento, AlimentoUpdateRequest alimentoUpdateRequest){

        var alimentoEncontrada = alimentoRepository.findById(idAlimento);

        if(alimentoEncontrada.isEmpty()){
            throw new AlimentoNaoEncontradoException("Alimento não encontrada.");
        }

        var alimento = alimentoEncontrada.get();

        //alimento.setSaborAlimento(alimentoUpdateRequest.getSaborAlimento());
        alimento.setTipoAlimento(alimentoUpdateRequest.getTipoAlimento());
        alimento.setMarcaAlimento(alimentoUpdateRequest.getMarcaAlimento());
        alimento.setPrecoAlimento(alimentoUpdateRequest.getPrecoAlimento());
        alimento.setOfertaAlimento(alimentoUpdateRequest.getOfertaAlimento());

        var alimentoSalva = alimentoRepository.save(alimento);

        return new AlimentoResponse(
                alimento.getIdArtefato(),
                alimento.getTipoAlimento(),
                alimento.getMarcaAlimento(),
                alimento.getSaborAlimento(),
                alimento.getUnidadeAlimento(),
                alimento.getPrecoAlimento(),
                alimento.getOfertaAlimento(),
                alimento.getArtefato().getDescricaoArtefato()
                //alimento.getListaImagensAlimento
        );
    }
}
