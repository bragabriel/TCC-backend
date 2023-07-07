package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoInactiveRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.Emprego.EmpregoCreateRequest;
import br.com.spotted.backend.domain.dto.Emprego.EmpregoResponse;
import br.com.spotted.backend.domain.dto.Emprego.EmpregoUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.Emprego;
import br.com.spotted.backend.exception.EmpregoNaoEncontradoException;
import br.com.spotted.backend.repository.EmpregoRepository;
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

        Page<Emprego> empregoPage = empregoRepository.findAll(searchRequest.parseToPageRequest());

        Page<EmpregoResponse> empregoResponsePage = empregoPage.map(EmpregoResponse::new);
        return new ResponseBase<>(empregoResponsePage);
    }

    public ResponseBase<EmpregoResponse> pesquisarPorId(Long id) {
        Optional<Emprego> empregoOptional = empregoRepository.findById(id);

        Emprego emprego = empregoOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estágio não encontrado."));

        EmpregoResponse empregoResponse = new EmpregoResponse(emprego);

        return new ResponseBase<>(empregoResponse);
    }

    public ResponseBase<EmpregoResponse> cadastrar(EmpregoCreateRequest novo) {

        ResponseBase<ArtefatoResponse> artefatoSalvo = artefatoService.cadastrar(novo.getArtefato());

        Artefato artefato = Artefato.builder()
                .tituloArtefato(artefatoSalvo.getObjetoRetorno().getTituloArtefato())
                .descricaoArtefato(artefatoSalvo.getObjetoRetorno().getDescricaoArtefato())
                .tipoArtefato(artefatoSalvo.getObjetoRetorno().getTipoArtefato())
                .ativo(artefatoSalvo.getObjetoRetorno().getAtivo())
                .dataCadastro(artefatoSalvo.getObjetoRetorno().getDataCadastro())
                .idUsuario(artefatoSalvo.getObjetoRetorno().getIdUsuario())
                .build();

        Emprego modeloDb = Emprego.builder()
                .idArtefato(artefatoSalvo.getObjetoRetorno().getIdArtefato())
                .beneficiosEmprego(novo.getBeneficiosEmprego())
                .localizacaoEmprego(novo.getLocalizacaoEmprego())
                .requisitosEmprego(novo.getRequisitosEmprego())
                .salarioEmprego(novo.getSalarioEmprego())
                .artefato(artefato)
                .build();

        Emprego empregoSalvo = empregoRepository.save(modeloDb);

        EmpregoResponse empregoResponse = new EmpregoResponse(empregoSalvo);
        return new ResponseBase<>(empregoResponse);
    }

    public EmpregoResponse atualizarEmprego(Long idEmprego, EmpregoUpdateRequest empregoUpdateRequest){

        Calendar cal = Calendar.getInstance();
        Date dataAtual = cal.getTime();

        var empregoEncontrado = empregoRepository.findById(idEmprego);
        if(empregoEncontrado.isEmpty()){
            throw new EmpregoNaoEncontradoException("Emprego não encontrada.");
        }

        Artefato artefato = new Artefato(empregoEncontrado.get().getArtefato());
        artefato.setTituloArtefato(empregoUpdateRequest.getTituloArtefato());
        artefato.setDescricaoArtefato(empregoUpdateRequest.getDescricaoArtefato());
        artefato.setDataAtualizacao(dataAtual);

        var emprego = empregoEncontrado.get();
        emprego.setBeneficiosEmprego(empregoUpdateRequest.getBeneficiosEmprego());
        emprego.setLocalizacaoEmprego(empregoUpdateRequest.getLocalizacaoEmprego());
        emprego.setRequisitosEmprego(empregoUpdateRequest.getRequisitosEmprego());
        emprego.setSalarioEmprego(empregoUpdateRequest.getSalarioEmprego());
        emprego.setArtefato(artefato);
        empregoRepository.save(emprego);

        return new EmpregoResponse(
                emprego.getIdArtefato(),
                emprego.getLocalizacaoEmprego(),
                emprego.getRequisitosEmprego(),
                emprego.getSalarioEmprego(),
                emprego.getBeneficiosEmprego(),
                emprego.getArtefato().getTituloArtefato(),
                emprego.getArtefato().getDescricaoArtefato()
        );
    }

    public ResponseEntity inativarEmprego(Long idEmprego) {

        var empregoEncontrada = empregoRepository.findById(idEmprego);
        if (empregoEncontrada.isEmpty()) {
            throw new EmpregoNaoEncontradoException("Emprego não encontrada.");
        }

        Artefato artefato = new Artefato(empregoEncontrada.get().getArtefato());
        artefato.setAtivo(false);
        ArtefatoInactiveRequest artefatoInactiveRequest = new ArtefatoInactiveRequest(artefato);
        artefatoService.desativarArtefato(empregoEncontrada.get().getIdArtefato(), artefatoInactiveRequest);

        return ResponseEntity.ok().build();
    }
}