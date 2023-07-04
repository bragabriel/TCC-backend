package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.Emprego.EmpregoCreateRequest;
import br.com.spotted.backend.domain.dto.Emprego.EmpregoResponse;
import br.com.spotted.backend.domain.dto.Emprego.EmpregoUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.Emprego;
import br.com.spotted.backend.exception.EstagioNaoEncontradoException;
import br.com.spotted.backend.repository.EmpregoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpregoService {

    private final EmpregoRepository empregoRepository;
    @Autowired
    private final ArtefatoService artefatoService;

    public ResponseBase<Page<EmpregoResponse>> pesquisar(PaginatedSearchRequest searchRequest) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Emprego> estagioPage = empregoRepository.findAll(searchRequest.parseToPageRequest());

        Page<EmpregoResponse> estagioResponsePage = estagioPage.map(EmpregoResponse::new);
        return new ResponseBase<>(estagioResponsePage);
    }

    public ResponseBase<EmpregoResponse> pesquisarPorId(Long id) {
        Optional<Emprego> estagioOptional = empregoRepository.findById(id);

        Emprego emprego = estagioOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estágio não encontrado."));

        EmpregoResponse empregoResponse = new EmpregoResponse(emprego);

        return new ResponseBase<>(empregoResponse);
    }

    public ResponseBase<EmpregoResponse> cadastrar(EmpregoCreateRequest novo) {

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

        Emprego modeloDb = new Emprego();
        modeloDb.setIdArtefato(idArtefato.getObjetoRetorno().getIdArtefato());
        modeloDb.setBeneficiosEmprego(novo.getBeneficiosEstagio());
        modeloDb.setLocalizacaoEmprego(novo.getLocalizacaoEstagio());
        modeloDb.setRequisitosEmprego(novo.getRequisitosEstagio());
        modeloDb.setSalarioEmprego(novo.getSalarioEstagio());
        modeloDb.setArtefato(artefato);

        //Salvando
        Emprego estagioSalvo = empregoRepository.save(modeloDb);

        // Mapeia de entidade para dto
        EmpregoResponse estagioResponse = new EmpregoResponse(estagioSalvo);
        return new ResponseBase<>(estagioResponse);
    }

//    public EmpregoResponse atualizarEstagio(Long idEstagio, EmpregoUpdateRequest empregoUpdateRequest){
//
//        var estagioEncontrado = empregoRepository.findById(idEstagio);
//
//        if(estagioEncontrado.isEmpty()){
//            throw new EstagioNaoEncontradoException("Estágio não encontrado.");
//        }
//
//        var estagio = estagioEncontrado.get();
//
//        estagio.setTituloEstagio(empregoUpdateRequest.getTituloEstagio());
//        estagio.setDescricaoEstagio(empregoUpdateRequest.getDescricaoEstagio());
//        estagio.setBeneficiosEstagio(empregoUpdateRequest.getBeneficiosEstagio());
//        estagio.setLocalizacaoEstagio(empregoUpdateRequest.getLocalizacaoEstagio());
//        estagio.setRequisitosEstagio(empregoUpdateRequest.getRequisitosEstagio());
//        estagio.setSalarioEstagio(empregoUpdateRequest.getSalarioEstagio());
//
//        var estagioSalvo = empregoRepository.save(estagio);
//
//        return new EmpregoResponse(
//                estagio.getIdEstagio(),
//                estagio.getTituloEstagio(),
//                estagio.getDescricaoEstagio(),
//                estagio.getLocalizacaoEstagio(),
//                estagio.getRequisitosEstagio(),
//                estagio.getSalarioEstagio(),
//                estagio.getBeneficiosEstagio(),
//                estagio.getListaImagensEstagio(),
//                estagio.getIdUsuario()
//        );
//    }

//    public EmpregoResponse deletar(Long idEstagio) {
//        var estagioEncontrado = empregoRepository.findById(idEstagio);
//
//        if (estagioEncontrado.isEmpty()) {
//            throw new EstagioNaoEncontradoException("Estágio não encontrado.");
//        }
//
//        var estagio = estagioEncontrado.get();
//        empregoRepository.delete(estagio);
//
//        return new EmpregoResponse(
//                estagio.getIdEstagio(),
//                estagio.getTituloEstagio(),
//                estagio.getDescricaoEstagio(),
//                estagio.getLocalizacaoEstagio(),
//                estagio.getRequisitosEstagio(),
//                estagio.getSalarioEstagio(),
//                estagio.getBeneficiosEstagio(),
//                estagio.getListaImagensEstagio(),
//                estagio.getIdUsuario()
//        );
//    }
}