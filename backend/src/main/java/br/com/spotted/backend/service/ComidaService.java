package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Comida.ComidaCreateRequest;
import br.com.spotted.backend.domain.dto.Comida.ComidaResponse;
import br.com.spotted.backend.domain.dto.Comida.ComidaUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioCreateRequest;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioResponse;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioUpdateRequest_NomeSobrenome;
import br.com.spotted.backend.domain.entity.Comida;
import br.com.spotted.backend.domain.entity.Usuario;
import br.com.spotted.backend.exception.ComidaNaoEncontradaException;
import br.com.spotted.backend.exception.UsuarioNaoEncontradoException;
import br.com.spotted.backend.repository.ComidaRepository;
import br.com.spotted.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComidaService {

    private final ComidaRepository comidaRepository;
    private final UsuarioRepository usuarioRepository;

    public ResponseBase<ComidaResponse> cadastrar(ComidaCreateRequest novo) {

        Comida modeloDb = new Comida();
        modeloDb.setNomeComida(novo.getNome());
        modeloDb.setTipoComida(novo.getTipo());
        modeloDb.setImagemComida(novo.getImagemComida());


        //Ir no repo de USUARIO e recuperar o Usuario desta Comida
        Optional<Usuario> usuarioOptinal = usuarioRepository.findById(novo.getIdUsuario());

        //Verifica se o responsável foi encontrado, caso o contratario lança exception
        Usuario usuarioSalvo = usuarioOptinal
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        //Setando o responsável (FK) na tabela de Video
        modeloDb.setUsuario(usuarioSalvo);

        //Salvando
        Comida comidaSalva = comidaRepository.save(modeloDb);

        // Mapeia de entidade para dto
        ComidaResponse comidaResponse = new ComidaResponse(comidaSalva);
        return new ResponseBase<>(comidaResponse);
    }

    public ResponseBase<Page<ComidaResponse>> pesquisar(PaginatedSearchRequest searchRequest) {

        // a Pagina atual não pode ser menor que 0
        if (searchRequest.getPaginaAtual() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 0");
        }
        // a quantidade de itens por pagina deve ser entre 1 e 50
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        // cria um objeto de consulta paginada(PageRequest) a partir dos parametros informados
        PageRequest pageRequest = PageRequest.of(searchRequest.getPaginaAtual(), searchRequest.getQtdPorPagina());

        // pesquisa no repositorio de customer usando a consulta paginada
        Page<Comida> comidaPage = comidaRepository.findAll(pageRequest);

        // Mapeia da entidade(Customer) para o DTO(CustomerResponse)
        Page<ComidaResponse> comidaResponsePage = comidaPage.map(ComidaResponse::new);
        return new ResponseBase<>(comidaResponsePage);
    }

    public ResponseBase<ComidaResponse> pesquisarPorId(Long id) {
        // Consulta o repositorio para procurar por um custumer pelo id
        Optional<Comida> comidaOptional = comidaRepository.findById(id);

        // Verifica se o custimer foi encontrado, caso o contratrio retorna um erro
        Comida comida = comidaOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comida não encontrada."));

        // Mapeia de entidade para dto
        ComidaResponse comidaResponse = new ComidaResponse(comida);

        return new ResponseBase<>(comidaResponse);
    }

    public ComidaResponse deletar(Long idUsuario) {
        var comidaEncontrada = comidaRepository.findById(idUsuario);

        if (comidaEncontrada.isEmpty()) {
            throw new ComidaNaoEncontradaException("Comida não encontrada.");
        }

        var comida = comidaEncontrada.get();
        comidaRepository.delete(comida);

        return new ComidaResponse(
                comida.getIdComida(),
                comida.getNomeComida(),
                comida.getTipoComida(),
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
        comida.setNomeComida(comidaUpdateRequest.getNomeComida());
        comida.setTipoComida(comidaUpdateRequest.getTipoComida());
        comida.setImagemComida(comidaUpdateRequest.getImagemComida());

        var comidaSalva = comidaRepository.save(comida);

        return new ComidaResponse(
                comidaSalva.getIdComida(),
                comidaSalva.getNomeComida(),
                comidaSalva.getTipoComida(),
                comidaSalva.getImagemComida(),
                comidaSalva.getIdUsuario()
        );
    }
}
