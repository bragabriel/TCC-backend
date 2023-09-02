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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtefatoService {

    private final ArtefatoRepository artefatoRepository;
    private final UsuarioService usuarioService;

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

        Artefato modeloDb = new Artefato();
        modeloDb.setTituloArtefato(novo.getTituloArtefato());
        modeloDb.setDescricaoArtefato(novo.getDescricaoArtefato());
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