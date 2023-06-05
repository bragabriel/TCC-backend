package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Transporte.TransporteCreateRequest;
import br.com.spotted.backend.domain.dto.Transporte.TransporteResponse;
import br.com.spotted.backend.domain.dto.Transporte.TransporteUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Transporte;
import br.com.spotted.backend.exception.TransporteNaoEncontradoException;
import br.com.spotted.backend.repository.TransporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransporteService {

    private final TransporteRepository transporteRepository;
    private final UsuarioService usuarioService;

    public ResponseBase<Page<TransporteResponse>> pesquisar(PaginatedSearchRequest searchRequest) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Transporte> transportePage = transporteRepository.findAll(searchRequest.parseToPageRequest());

        Page<TransporteResponse> transporteResponsePage = transportePage.map(TransporteResponse::new);
        return new ResponseBase<>(transporteResponsePage);
    }

    public ResponseBase<TransporteResponse> pesquisarPorId(Long id) {
        Optional<Transporte> transporteOptional = transporteRepository.findById(id);

        Transporte transporte = transporteOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transporte não encontrada."));

        TransporteResponse transporteResponse = new TransporteResponse(transporte);

        return new ResponseBase<>(transporteResponse);
    }

    public ResponseBase<TransporteResponse> cadastrar(TransporteCreateRequest novo) {

        Transporte modeloDb = new Transporte();
        modeloDb.setTituloTransporte(novo.getTituloTransporte());
        modeloDb.setDescricaoTransporte(novo.getDescricaoTransporte());
        modeloDb.setInformacoesVeiculoTransporte(novo.getInformacoesVeiculoTransporte());
        modeloDb.setInformacoesCondutorTransporte(novo.getInformacoesCondutorTransporte());
        modeloDb.setQtdAssentosPreenchidosTransporte(novo.getQtdAssentosPreenchidosTransporte());
        modeloDb.setQtdAssentosTotalTransporte(novo.getQtdAssentosTotalTransporte());
        modeloDb.setCidadeTransporte(novo.getCidadeTransporte());
        modeloDb.setPeriodoTransporte(novo.getPeriodoTransporte());
        modeloDb.setIdUsuario(novo.getIdUsuario());

        //Validação de usuário no banco de dados
        usuarioService.pesquisarPorId(novo.getIdUsuario());

        //Salvando
        Transporte transporteSalva = transporteRepository.save(modeloDb);

        // Mapeia de entidade para dto
        TransporteResponse transporteResponse = new TransporteResponse(transporteSalva);
        return new ResponseBase<>(transporteResponse);
    }

    public TransporteResponse deletar(Long idTransporte) {
        var transporteEncontrada = transporteRepository.findById(idTransporte);

        if (transporteEncontrada.isEmpty()) {
            throw new TransporteNaoEncontradoException("Transporte não encontrado.");
        }

        var transporte = transporteEncontrada.get();
        transporteRepository.delete(transporte);

        return new TransporteResponse(
                transporte.getIdTransporte(),
                transporte.getTituloTransporte(),
                transporte.getDescricaoTransporte(),
                transporte.getInformacoesVeiculoTransporte(),
                transporte.getInformacoesCondutorTransporte(),
                transporte.getQtdAssentosPreenchidosTransporte(),
                transporte.getQtdAssentosTotalTransporte(),
                transporte.getCidadeTransporte(),
                transporte.getPeriodoTransporte(),
                transporte.getListaImagensTransporte(),
                transporte.getIdUsuario()
        );
    }

    public TransporteResponse atualizarTransporte(Long idTransporte, TransporteUpdateRequest transporteUpdateRequest){

        var comidaEncontrada = transporteRepository.findById(idTransporte);

        if(comidaEncontrada.isEmpty()){
            throw new TransporteNaoEncontradoException("Transporte não encontrado.");
        }

        var transporte = comidaEncontrada.get();
        transporte.setTituloTransporte(transporteUpdateRequest.getTituloTransporte());
        transporte.setDescricaoTransporte(transporteUpdateRequest.getDescricaoTransporte());
        transporte.setInformacoesVeiculoTransporte(transporteUpdateRequest.getInformacoesVeiculoTransporte());
        transporte.setInformacoesCondutorTransporte(transporteUpdateRequest.getInformacoesCondutorTransporte());
        transporte.setQtdAssentosPreenchidosTransporte(transporteUpdateRequest.getQtdAssentosPreenchidosTransporte());
        transporte.setQtdAssentosTotalTransporte(transporteUpdateRequest.getQtdAssentosTotalTransporte());
        transporte.setCidadeTransporte(transporteUpdateRequest.getCidadeTransporte());
        transporte.setPeriodoTransporte(transporteUpdateRequest.getPeriodoTransporte());

        var transporteSalva = transporteRepository.save(transporte);

        return new TransporteResponse(
                transporte.getIdTransporte(),
                transporte.getTituloTransporte(),
                transporte.getDescricaoTransporte(),
                transporte.getInformacoesVeiculoTransporte(),
                transporte.getInformacoesCondutorTransporte(),
                transporte.getQtdAssentosPreenchidosTransporte(),
                transporte.getQtdAssentosTotalTransporte(),
                transporte.getCidadeTransporte(),
                transporte.getPeriodoTransporte(),
                transporte.getListaImagensTransporte(),
                transporte.getIdUsuario()
        );
    }
}

