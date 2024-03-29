package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoInactiveRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.Emprego.EmpregoCreateRequest;
import br.com.spotted.backend.domain.dto.Emprego.EmpregoArtefatoResponse;
import br.com.spotted.backend.domain.dto.Emprego.EmpregoUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.Emprego;
import br.com.spotted.backend.domain.entity.TipoArtefato;
import br.com.spotted.backend.exception.EmpregoNotFoundException;
import br.com.spotted.backend.repository.EmpregoRepository;
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
public class EmpregoService {

    private final EmpregoRepository empregoRepository;
    @Autowired
    private final ArtefatoService artefatoService;


    public ResponseBase<List<EmpregoArtefatoResponse>> pesquisar() {
        Iterable<Emprego> empregos = empregoRepository.findAllEmpregosWithArtefatoAtivo();
        List<EmpregoArtefatoResponse> empregoArtefatoResponse = new ArrayList<>();

        for (Emprego emprego : empregos) {
            empregoArtefatoResponse.add(new EmpregoArtefatoResponse(emprego));
        }
        return new ResponseBase<>(empregoArtefatoResponse);
    }

    public ResponseBase<Page<EmpregoArtefatoResponse>> pesquisarPaginado(PaginatedSearchRequest searchRequest) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Emprego> empregoPage = empregoRepository.findAll(searchRequest.parseToPageRequest());

        Page<EmpregoArtefatoResponse> empregoResponsePage = empregoPage.map(EmpregoArtefatoResponse::new);
        return new ResponseBase<>(empregoResponsePage);
    }

    public ResponseBase<EmpregoArtefatoResponse> pesquisarPorId(Long id) {
        Optional<Emprego> empregoOptional = empregoRepository.findById(id);

        Emprego emprego = empregoOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estágio não encontrado."));

        EmpregoArtefatoResponse empregoArtefatoResponse = new EmpregoArtefatoResponse(emprego);

        return new ResponseBase<>(empregoArtefatoResponse);
    }

    public Long cadastrar(EmpregoCreateRequest novo) {

        Calendar cal = Calendar.getInstance();
        Date dataAtual = cal.getTime();

        var artefatoData = novo.getArtefato();

        Artefato artefato = Artefato.builder()
                .tituloArtefato(artefatoData.getTituloArtefato())
                .descricaoArtefato(artefatoData.getDescricaoArtefato())
                .ativo(true)
                .tipoArtefato(TipoArtefato.EMPREGO)
                .dataCadastro(dataAtual)
                .idUsuario(artefatoData.getIdUsuario())
                .build();

        Emprego modeloDb = Emprego.builder()
                .beneficiosEmprego(novo.getBeneficiosEmprego())
                .localizacaoEmprego(novo.getLocalizacaoEmprego())
                .requisitosEmprego(novo.getRequisitosEmprego())
                .salarioEmprego(novo.getSalarioEmprego())
                .contatoEmprego(novo.getContatoEmprego())
                .linkVagaEmprego(novo.getLinkVagaEmprego())
                .empresaEmprego(novo.getEmpresaEmprego())
                .cidadeEmprego(novo.getCidadeEmprego())
                .estadoEmprego(novo.getEstadoEmprego())
                .experienciaEmprego(novo.getExperienciaEmprego())
                .tipoVagaEmprego(novo.getTipoVagaEmprego())
                .presencialEmprego(novo.getPresencialEmprego())
                .artefato(artefato)
                .build();

        Emprego empregoSalvo = empregoRepository.save(modeloDb);

        return empregoSalvo.getArtefato().getIdArtefato();
    }

    public EmpregoArtefatoResponse atualizarEmprego(Long idEmprego, EmpregoUpdateRequest empregoUpdateRequest){

        Calendar cal = Calendar.getInstance();
        Date dataAtual = cal.getTime();

        var empregoEncontrado = empregoRepository.findById(idEmprego);
        if(empregoEncontrado.isEmpty()){
            throw new EmpregoNotFoundException("Emprego não encontrada.");
        }

        Artefato artefato = new Artefato(empregoEncontrado.get().getArtefato());
        artefato.setTituloArtefato(empregoUpdateRequest.getTituloArtefato());
        artefato.setDescricaoArtefato(empregoUpdateRequest.getDescricaoArtefato());
        artefato.setListaImagens(empregoEncontrado.get().getArtefato().getListaImagens());
        artefato.setDataAtualizacao(dataAtual);

        var emprego = empregoEncontrado.get();
        emprego.setBeneficiosEmprego(empregoUpdateRequest.getBeneficiosEmprego());
        emprego.setLocalizacaoEmprego(empregoUpdateRequest.getLocalizacaoEmprego());
        emprego.setRequisitosEmprego(empregoUpdateRequest.getRequisitosEmprego());
        emprego.setSalarioEmprego(empregoUpdateRequest.getSalarioEmprego());
        emprego.setContatoEmprego(empregoUpdateRequest.getContatoEmprego());
        emprego.setLinkVagaEmprego(empregoUpdateRequest.getLinkVagaEmprego());
        emprego.setEmpresaEmprego(empregoUpdateRequest.getEmpresaEmprego());
        emprego.setCidadeEmprego(empregoUpdateRequest.getCidadeEmprego());
        emprego.setEstadoEmprego(empregoUpdateRequest.getEstadoEmprego());
        emprego.setExperienciaEmprego(empregoUpdateRequest.getExperienciaEmprego());
        emprego.setTipoVagaEmprego(empregoUpdateRequest.getTipoVagaEmprego());
        emprego.setPresencialEmprego(empregoUpdateRequest.getPresencialEmprego());

        emprego.setArtefato(artefato);
        empregoRepository.save(emprego);

        return new EmpregoArtefatoResponse(
                emprego.getIdArtefato(),
                emprego.getLocalizacaoEmprego(),
                emprego.getRequisitosEmprego(),
                emprego.getSalarioEmprego(),
                emprego.getBeneficiosEmprego(),
                emprego.getContatoEmprego(),
                emprego.getLinkVagaEmprego(),
                emprego.getEmpresaEmprego(),
                emprego.getCidadeEmprego(),
                emprego.getEstadoEmprego(),
                emprego.getExperienciaEmprego(),
                emprego.getTipoVagaEmprego(),
                emprego.getPresencialEmprego(),
                emprego.getArtefato().getTituloArtefato(),
                emprego.getArtefato().getDescricaoArtefato()
        );
    }

    public ResponseEntity inativarEmprego(Long idEmprego) {

        var empregoEncontrada = empregoRepository.findById(idEmprego);
        if (empregoEncontrada.isEmpty()) {
            throw new EmpregoNotFoundException("Emprego não encontrada.");
        }

        Artefato artefato = new Artefato(empregoEncontrada.get().getArtefato());
        artefato.setAtivo(false);
        ArtefatoInactiveRequest artefatoInactiveRequest = new ArtefatoInactiveRequest(artefato);
        artefatoService.desativarArtefato(empregoEncontrada.get().getIdArtefato(), artefatoInactiveRequest);

        return ResponseEntity.ok().build();
    }
}