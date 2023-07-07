package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoCreateRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoInactiveRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.TipoArtefato;
import br.com.spotted.backend.exception.ArtefatoNotFoundException;
import br.com.spotted.backend.exception.UsuarioNotFoundException;
import br.com.spotted.backend.repository.ArtefatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtefatoService {

    private final ArtefatoRepository artefatoRepository;
    private final UsuarioService usuarioService;

    public ResponseBase<Page<ArtefatoResponse>> pesquisar(PaginatedSearchRequest searchRequest, String tipo, Boolean ativo) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }

        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Artefato> page;
        if ("alimento".equalsIgnoreCase(tipo)) {
            page = artefatoRepository.findAllWithAlimento("alimento", searchRequest.parseToPageRequest(), ativo);
        }
        else if("emprego".equalsIgnoreCase(tipo)){
            page = artefatoRepository.findAllWithEmprego("emprego", searchRequest.parseToPageRequest(), ativo);
        }
        else if("festa".equalsIgnoreCase(tipo)){
            page = artefatoRepository.findAllWithFesta("festa", searchRequest.parseToPageRequest(), ativo);
        }
        else if("moradia".equalsIgnoreCase(tipo)){
            page = artefatoRepository.findAllWithMoradia("moradia", searchRequest.parseToPageRequest(), ativo);
        }
        else if("objeto".equalsIgnoreCase(tipo)){
            page = artefatoRepository.findAllWithObjeto("objeto", searchRequest.parseToPageRequest(), ativo);
        }
        else if("transporte".equalsIgnoreCase(tipo)){
            page = artefatoRepository.findAllWithTransporte("transporte", searchRequest.parseToPageRequest(), ativo);
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

        Calendar cal = Calendar.getInstance();
        Date dataAtual = cal.getTime();

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
        modeloDb.setAtivo(true);
        modeloDb.setDataCadastro(dataAtual);
        modeloDb.setIdUsuario(novo.getIdUsuario());

        Artefato artefatoSalvo = artefatoRepository.save(modeloDb);

        ArtefatoResponse artefatoResponse = new ArtefatoResponse(artefatoSalvo);
        return new ResponseBase<>(artefatoResponse);
    }

    public ArtefatoResponse atualizarArtefato(Long idArtefato, ArtefatoUpdateRequest artefatoUpdateRequest) {

        Calendar cal = Calendar.getInstance();
        Date dataAtual = cal.getTime();

        var artefatoEncontrado = artefatoRepository.findById(idArtefato);

        if (artefatoEncontrado.isEmpty()) {
            throw new ArtefatoNotFoundException("Artefato não encontrado.");
        }

        var artefato = artefatoEncontrado.get();
        artefato.setTituloArtefato(artefatoUpdateRequest.getTituloArtefato());
        artefato.setDescricaoArtefato(artefatoUpdateRequest.getDescricaoArtefato());
        artefato.setAtivo(artefatoUpdateRequest.getAtivo());
        artefato.setDataAtualizacao(dataAtual);

        artefatoRepository.save(artefato);

        return new ArtefatoResponse(
               artefato
        );
    }

    public ArtefatoResponse desativarArtefato(Long idArtefato, ArtefatoInactiveRequest artefatoInactiveRequest) {

        Calendar cal = Calendar.getInstance();
        Date dataAtual = cal.getTime();

        var artefatoEncontrado = artefatoRepository.findById(idArtefato);

        if (artefatoEncontrado.isEmpty()) {
            throw new ArtefatoNotFoundException("Artefato não encontrado.");
        }

        var artefato = artefatoEncontrado.get();

        artefato.setAtivo(artefatoInactiveRequest.getAtivo());
        artefato.setDataInativo(dataAtual);

        artefatoRepository.save(artefato);

        return new ArtefatoResponse(
                artefato
        );
    }

    public ArtefatoResponse deletar(Long idArtefato) throws UsuarioNotFoundException {
        var artefatoEncontrado = artefatoRepository.findById(idArtefato);

        if (artefatoEncontrado.isEmpty()) {
            throw new ArtefatoNotFoundException("Tarefa não encontrada.");
        }

        var artefato = artefatoEncontrado.get();
        artefatoRepository.delete(artefato);

        return new ArtefatoResponse(
                artefato
        );
    }
}