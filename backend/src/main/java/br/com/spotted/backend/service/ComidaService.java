package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Comida.ComidaCreateRequest;
import br.com.spotted.backend.domain.dto.Comida.ComidaResponse;
import br.com.spotted.backend.domain.dto.Comida.ComidaUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Comida;
import br.com.spotted.backend.exception.ComidaNaoEncontradaException;
import br.com.spotted.backend.repository.ComidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComidaService {

    private final ComidaRepository comidaRepository;
    private final UsuarioService usuarioService;

    public ResponseBase<Page<ComidaResponse>> pesquisar(PaginatedSearchRequest searchRequest) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Comida> comidaPage = comidaRepository.findAll(searchRequest.parseToPageRequest());

        Page<ComidaResponse> comidaResponsePage = comidaPage.map(ComidaResponse::new);
        return new ResponseBase<>(comidaResponsePage);
    }

    public ResponseBase<ComidaResponse> pesquisarPorId(Long id) {
        Optional<Comida> comidaOptional = comidaRepository.findById(id);

        Comida comida = comidaOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comida não encontrada."));

        ComidaResponse comidaResponse = new ComidaResponse(comida);

        return new ResponseBase<>(comidaResponse);
    }

    public ResponseBase<ComidaResponse> cadastrar(ComidaCreateRequest novo) {

        Comida modeloDb = new Comida();
        modeloDb.setTituloComida(novo.getTituloComida());
        modeloDb.setDescricaoComida(novo.getDescricaoComida());
        modeloDb.setTipoComida(novo.getTipoComida());
        modeloDb.setMarcaComida(novo.getMarcaComida());
        modeloDb.setPreco_comida(novo.getPrecoComida());
        modeloDb.setOfertaComida(novo.getOfertaComida());
        modeloDb.setImagemComida(novo.getImagemComida());
        modeloDb.setIdUsuario(novo.getIdUsuario());

        //Validação de usuário no banco de dados
        usuarioService.pesquisarPorId(novo.getIdUsuario());

        //Salvando
        Comida comidaSalva = comidaRepository.save(modeloDb);

        // Mapeia de entidade para dto
        ComidaResponse comidaResponse = new ComidaResponse(comidaSalva);
        return new ResponseBase<>(comidaResponse);
    }

    public ComidaResponse deletar(Long idComida) {
        var comidaEncontrada = comidaRepository.findById(idComida);

        if (comidaEncontrada.isEmpty()) {
            throw new ComidaNaoEncontradaException("Comida não encontrada.");
        }

        var comida = comidaEncontrada.get();
        comidaRepository.delete(comida);

        return new ComidaResponse(
                comida.getIdComida(),
                comida.getTituloComida(),
                comida.getDescricaoComida(),
                comida.getTipoComida(),
                comida.getMarcaComida(),
                comida.getOfertaComida(),
                comida.getImagemComida(),
                comida.getIdUsuario()
        );
    }

    public ComidaResponse atualizarComida(Long idComida, ComidaUpdateRequest comidaUpdateRequest){

        var comidaEncontrada = comidaRepository.findById(idComida);

        if(comidaEncontrada.isEmpty()){
            throw new ComidaNaoEncontradaException("Comida não encontrada.");
        }

        var comida = comidaEncontrada.get();
        comida.setTituloComida(comidaUpdateRequest.getTituloComida());
        comida.setTipoComida(comidaUpdateRequest.getTipoComida());
        comida.setImagemComida(comidaUpdateRequest.getImagemComida());

        var comidaSalva = comidaRepository.save(comida);

        return new ComidaResponse(
                comida.getIdComida(),
                comida.getTituloComida(),
                comida.getDescricaoComida(),
                comida.getTipoComida(),
                comida.getMarcaComida(),
                comida.getOfertaComida(),
                comida.getImagemComida(),
                comida.getIdUsuario()
        );
    }
}
