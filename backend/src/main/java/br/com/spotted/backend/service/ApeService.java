package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Ape.ApeCreateRequest;
import br.com.spotted.backend.domain.dto.Ape.ApeResponse;
import br.com.spotted.backend.domain.dto.Ape.ApeUpdateRequest;
import br.com.spotted.backend.domain.dto.Comida.ComidaCreateRequest;
import br.com.spotted.backend.domain.dto.Comida.ComidaResponse;
import br.com.spotted.backend.domain.dto.Comida.ComidaUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Ape;
import br.com.spotted.backend.domain.entity.Comida;
import br.com.spotted.backend.domain.entity.Usuario;
import br.com.spotted.backend.exception.ApeNaoEncontradoException;
import br.com.spotted.backend.exception.ComidaNaoEncontradaException;
import br.com.spotted.backend.repository.ApeRepository;
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
public class ApeService {

    private final ApeRepository apeRepository;
    private final UsuarioRepository usuarioRepository;

    public ResponseBase<ApeResponse> cadastrar(ApeCreateRequest novo) {

        Ape modeloDb = new Ape();
        modeloDb.setTituloApe(novo.getTituloApe());
        modeloDb.setDescricaoApe(novo.getDescricaoApe());
        modeloDb.setQtdMoradoresAtuaisApe(novo.getQtdMoradoresAtuaisApe());
        modeloDb.setQtdMoradoresPermitidoApe(novo.getQtdMoradoresPermitidoApe());
        modeloDb.setPrecoAluguelTotalApe(novo.getPrecoAluguelTotalApe());
        modeloDb.setPrecoAluguelPorPessoaApe(novo.getPrecoAluguelPorPessoaApe());
        modeloDb.setVagaGaragemApe(novo.getVagaGaragemApe());
        modeloDb.setAnimaisEstimacaoApe(novo.getAnimaisEstimacaoApe());
        modeloDb.setImagemApe(novo.getImagemComida());

        //Buscar no repo de USUARIO e recuperar o Usuario desta Comida
        Optional<Usuario> usuarioOptinal = usuarioRepository.findById(novo.getIdUsuario());

        //Verifica se o responsável foi encontrado, caso o contratario lança exception
        Usuario usuarioSalvo = usuarioOptinal.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        //Setando o responsável (FK) na tabela de Video
        modeloDb.setUsuario(usuarioSalvo);

        //Salvando
        Ape apeSalvo = apeRepository.save(modeloDb);

        // Mapeia de entidade para dto
        ApeResponse apeResponse = new ApeResponse(apeSalvo);
        return new ResponseBase<>(apeResponse);
    }

    public ResponseBase<Page<ApeResponse>> pesquisar(PaginatedSearchRequest searchRequest) {

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
        Page<Ape> apePage = apeRepository.findAll(pageRequest);

        //Mapeia da entidade(APE) para o DTO(ApeResponse)
        Page<ApeResponse> apeResponsePage = apePage.map(ApeResponse::new);
        return new ResponseBase<>(apeResponsePage);
    }

    public ResponseBase<ApeResponse> pesquisarPorId(Long id) {
        // Consulta o repositorio para procurar por um custumer pelo id
        Optional<Ape> apeOptional = apeRepository.findById(id);

        // Verifica se o custimer foi encontrado, caso o contratrio retorna um erro
        Ape ape = apeOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Apê não encontrado."));

        // Mapeia de entidade para dto
        ApeResponse apeResponse = new ApeResponse(ape);

        return new ResponseBase<>(apeResponse);
    }

    public ApeResponse deletar(Long idApe) {
        var apeEncontrado = apeRepository.findById(idApe);

        if (apeEncontrado.isEmpty()) {
            throw new ApeNaoEncontradoException("Apê não encontrado.");
        }

        var ape = apeEncontrado.get();
        apeRepository.delete(ape);

        return new ApeResponse(
            ape.getIdApe(),
            ape.getTituloApe(),
            ape.getDescricaoApe(),
            ape.getLocalizacaoApe(),
            ape.getQtdMoradoresAtuaisApe(),
            ape.getQtdMoradoresPermitidoApe(),
            ape.getPrecoAluguelTotalApe(),
            ape.getPrecoAluguelPorPessoaApe(),
            ape.getVagaGaragemApe(),
            ape.getAnimaisEstimacaoApe(),
            ape.getImagemApe(),
            ape.getIdUsuario()
        );
    }

    public ApeResponse atualizarApe(Long idApe, ApeUpdateRequest apeUpdateRequest) {

        var apeEncontrado = apeRepository.findById(idApe);

        if (apeEncontrado.isEmpty()) {
            throw new ComidaNaoEncontradaException("Ape não encontrado.");
        }

        var ape = apeEncontrado.get();

        ape.setTituloApe(apeUpdateRequest.getTituloApe());
        ape.setDescricaoApe(apeUpdateRequest.getDescricaoApe());
        ape.setQtdMoradoresAtuaisApe(apeUpdateRequest.getQtdMoradoresAtuaisApe());
        ape.setQtdMoradoresPermitidoApe(apeUpdateRequest.getQtdMoradoresPermitidoApe());
        ape.setPrecoAluguelTotalApe(apeUpdateRequest.getPrecoAluguelTotalApe());
        ape.setPrecoAluguelPorPessoaApe(apeUpdateRequest.getPrecoAluguelPorPessoaApe());
        ape.setVagaGaragemApe(apeUpdateRequest.getVagaGaragemApe());
        ape.setAnimaisEstimacaoApe(apeUpdateRequest.getAnimaisEstimacaoApe());
        ape.setImagemApe(apeUpdateRequest.getImagemComida());

        var apeSalvo = apeRepository.save(ape);

        return new ApeResponse(
                ape.getIdApe(),
                ape.getTituloApe(),
                ape.getDescricaoApe(),
                ape.getLocalizacaoApe(),
                ape.getQtdMoradoresAtuaisApe(),
                ape.getQtdMoradoresPermitidoApe(),
                ape.getPrecoAluguelTotalApe(),
                ape.getPrecoAluguelPorPessoaApe(),
                ape.getVagaGaragemApe(),
                ape.getAnimaisEstimacaoApe(),
                ape.getImagemApe(),
                ape.getIdUsuario()
        );
    }
}
