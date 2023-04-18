package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Estagio.EstagioCreateRequest;
import br.com.spotted.backend.domain.dto.Estagio.EstagioResponse;
import br.com.spotted.backend.domain.dto.Estagio.EstagioUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Estagio;
import br.com.spotted.backend.exception.EstagioNaoEncontradoException;
import br.com.spotted.backend.repository.EstagioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstagioService {

    private final EstagioRepository estagioRepository;
    private final UsuarioService usuarioService;

    public ResponseBase<Page<EstagioResponse>> pesquisar(PaginatedSearchRequest searchRequest) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Estagio> estagioPage = estagioRepository.findAll(searchRequest.parseToPageRequest());

        Page<EstagioResponse> estagioResponsePage = estagioPage.map(EstagioResponse::new);
        return new ResponseBase<>(estagioResponsePage);
    }

    public ResponseBase<EstagioResponse> pesquisarPorId(Long id) {
        Optional<Estagio> estagioOptional = estagioRepository.findById(id);

        Estagio estagio = estagioOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estágio não encontrado."));

        EstagioResponse estagioResponse = new EstagioResponse(estagio);

        return new ResponseBase<>(estagioResponse);
    }

    public ResponseBase<EstagioResponse> cadastrar(EstagioCreateRequest novo) {

        Estagio modeloDb = new Estagio();
        modeloDb.setTituloEstagio(novo.getTituloEstagio());
        modeloDb.setDescricaoEstagio(novo.getDescricaoEstagio());
        modeloDb.setBeneficiosEstagio(novo.getBeneficiosEstagio());
        modeloDb.setLocalizacaoEstagio(novo.getLocalizacaoEstagio());
        modeloDb.setRequisitosEstagio(novo.getRequisitosEstagio());
        modeloDb.setSalarioEstagio(novo.getSalarioEstagio());
        modeloDb.setIdUsuario(novo.getIdUsuario());

        //Validação de usuário no banco de dados
        usuarioService.pesquisarPorId(novo.getIdUsuario());

        //Salvando
        Estagio estagioSalvo = estagioRepository.save(modeloDb);

        // Mapeia de entidade para dto
        EstagioResponse estagioResponse = new EstagioResponse(estagioSalvo);
        return new ResponseBase<>(estagioResponse);
    }

    public EstagioResponse deletar(Long idEstagio) {
        var estagioEncontrado = estagioRepository.findById(idEstagio);

        if (estagioEncontrado.isEmpty()) {
            throw new EstagioNaoEncontradoException("Estágio não encontrado.");
        }

        var estagio = estagioEncontrado.get();
        estagioRepository.delete(estagio);

        return new EstagioResponse(
                estagio.getIdEstagio(),
                estagio.getTituloEstagio(),
                estagio.getDescricaoEstagio(),
                estagio.getLocalizacaoEstagio(),
                estagio.getRequisitosEstagio(),
                estagio.getSalarioEstagio(),
                estagio.getBeneficiosEstagio(),
                estagio.getListaImagensEstagio(),
                estagio.getIdUsuario()
        );
    }

    public EstagioResponse atualizarEstagio(Long idEstagio, EstagioUpdateRequest estagioUpdateRequest){

        var estagioEncontrado = estagioRepository.findById(idEstagio);

        if(estagioEncontrado.isEmpty()){
            throw new EstagioNaoEncontradoException("Estágio não encontrado.");
        }

        var estagio = estagioEncontrado.get();

        estagio.setTituloEstagio(estagioUpdateRequest.getTituloEstagio());
        estagio.setDescricaoEstagio(estagioUpdateRequest.getDescricaoEstagio());
        estagio.setBeneficiosEstagio(estagioUpdateRequest.getBeneficiosEstagio());
        estagio.setLocalizacaoEstagio(estagioUpdateRequest.getLocalizacaoEstagio());
        estagio.setRequisitosEstagio(estagioUpdateRequest.getRequisitosEstagio());
        estagio.setSalarioEstagio(estagioUpdateRequest.getSalarioEstagio());

        var estagioSalvo = estagioRepository.save(estagio);

        return new EstagioResponse(
                estagio.getIdEstagio(),
                estagio.getTituloEstagio(),
                estagio.getDescricaoEstagio(),
                estagio.getLocalizacaoEstagio(),
                estagio.getRequisitosEstagio(),
                estagio.getSalarioEstagio(),
                estagio.getBeneficiosEstagio(),
                estagio.getListaImagensEstagio(),
                estagio.getIdUsuario()
        );
    }
}
