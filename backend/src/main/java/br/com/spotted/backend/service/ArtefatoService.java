package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoCreateRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.TipoArtefato;
import br.com.spotted.backend.exception.ArtefatoNaoEncontradoException;
import br.com.spotted.backend.exception.UsuarioNaoEncontradoException;
import br.com.spotted.backend.repository.ArtefatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtefatoService {

    private final ArtefatoRepository artefatoRepository;
    private final UsuarioService usuarioService;
    private final AlimentoService alimentoService;
    private final EstagioService estagioService;
    private final FestaService festaService;
    private final MoradiaService moradiaService;
    private final ObjetoService objetoService;
    private final TransporteService transporteService;

    public ResponseBase<Page<ArtefatoResponse>> pesquisar(PaginatedSearchRequest searchRequest, String tipo) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }

        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Artefato> page;
        if ("alimento".equalsIgnoreCase(tipo)) {
            page = artefatoRepository.findAllWithAlimento("alimento", searchRequest.parseToPageRequest());
        }
        else if("transporte".equalsIgnoreCase(tipo)){
            page = artefatoRepository.findAllWithTransporte("transporte", searchRequest.parseToPageRequest());
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe este tipo de Artefato");
        }

        Page<ArtefatoResponse> artefatoResponsePage = page.map(ArtefatoResponse::new);
        return new ResponseBase<>(artefatoResponsePage);
    }

    public ResponseBase<ArtefatoResponse> pesquisarPorId(Long id) {
        Optional<Artefato> artefatoOptional = artefatoRepository.findById(id);

        Artefato artefato = artefatoOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artefato não encontrado"));

        ArtefatoResponse artefatoResponse = new ArtefatoResponse(artefato);

        return new ResponseBase<>(artefatoResponse);
    }

    public ResponseBase<ArtefatoResponse> cadastrar(ArtefatoCreateRequest novo) {

        //Validação de usuário no banco de dados
        usuarioService.pesquisarPorId(novo.getIdUsuario());

        //Validando tipo de artefato
        TipoArtefato tipoArtefato;
        try {
            tipoArtefato = TipoArtefato.valueOf(novo.getTipoArtefato().toString().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de artefato inválido");
        }

        Artefato modeloDb = new Artefato();
        modeloDb.setTituloArtefato(novo.getTituloArtefato());
        modeloDb.setDescricaoArtefato(novo.getDescricaoArtefato());
        modeloDb.setTipoArtefato(novo.getTipoArtefato().toString().toUpperCase());
        modeloDb.setAtivo(novo.getAtivo());
        modeloDb.setDataCadastro(novo.getDataCadastro());
        modeloDb.setIdUsuario(novo.getIdUsuario());

        Artefato artefatoSalvo = artefatoRepository.save(modeloDb);

//        switch (tipoArtefato) {
//            case ALIMENTO:
//                alimentoService.cadastrar(artefatoSalvo);
//                break;
//            case ESTAGIO:
//                estagioService.cadastrar(artefatoSalvo);
//                break;
//            case FESTA:
//                festaService.cadastrar(artefatoSalvo);
//                break;
//            case MORADIA:
//                moradiaService.cadastrar(artefatoSalvo);
//                break;
//            case OBJETO:
//                objetoService.cadastrar(artefatoSalvo);
//                break;
//            case TRANSPORTE:
//                transporteService.cadastrar(artefatoSalvo);
//                break;
//            default:
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de artefato inválido");
//        }

        ArtefatoResponse artefatoResponse = new ArtefatoResponse(artefatoSalvo);
        return new ResponseBase<>(artefatoResponse);
    }
    public ArtefatoResponse deletar(Long idArtefato) throws UsuarioNaoEncontradoException {
        var artefatoEncontrado = artefatoRepository.findById(idArtefato);

        if (artefatoEncontrado.isEmpty()) {
            throw new ArtefatoNaoEncontradoException("Tarefa não encontrada.");
        }

        var artefato = artefatoEncontrado.get();
        artefatoRepository.delete(artefato);

        return new ArtefatoResponse(
                artefato
        );
    }

    public ArtefatoResponse atualizarArtefato(Long idArtefato, ArtefatoUpdateRequest artefatoUpdateRequest) {

        var artefatoEncontrado = artefatoRepository.findById(idArtefato);

        if (artefatoEncontrado.isEmpty()) {
            throw new ArtefatoNaoEncontradoException("Artefato não encontrado.");
        }

        var artefato = artefatoEncontrado.get();

        artefato.setTituloArtefato(artefatoUpdateRequest.getTituloArtefato());
        artefato.setDescricaoArtefato(artefatoUpdateRequest.getDescricaoArtefato());
        artefato.setTipoArtefato(artefatoUpdateRequest.getTipoArtefato());
        artefato.setAtivo(artefatoUpdateRequest.getAtivo());
        artefato.setDataCadastro(artefatoUpdateRequest.getDataCadastro());

        var artefatoSalvo = artefatoRepository.save(artefato);

        return new ArtefatoResponse(
               artefato
        );
    }
}