package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.Transporte.TransporteCreateRequest;
import br.com.spotted.backend.domain.dto.Transporte.TransporteResponse;
import br.com.spotted.backend.domain.dto.Transporte.TransporteUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.Transporte;
import br.com.spotted.backend.exception.TransporteNaoEncontradoException;
import br.com.spotted.backend.repository.TransporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransporteService {

    private final TransporteRepository transporteRepository;
    @Autowired
    private final ArtefatoService artefatoService;

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

//    public ResponseBase<TransporteResponse> cadastrar(TransporteCreateRequest novo) {
//
//        //Cadastrando o artefato
//        ResponseBase<ArtefatoResponse> idArtefato = artefatoService.cadastrar(novo.getArtefato());
//
//        //Cadastrando o Alimento
//        Artefato artefato = new Artefato();
//        artefato.setIdArtefato(idArtefato.getObjetoRetorno().getIdArtefato());
//        artefato.setTituloArtefato(idArtefato.getObjetoRetorno().getTituloArtefato());
//        artefato.setDescricaoArtefato(idArtefato.getObjetoRetorno().getDescricaoArtefato());
//        artefato.setTipoArtefato(idArtefato.getObjetoRetorno().getTipoArtefato());
//        artefato.setAtivo(idArtefato.getObjetoRetorno().getAtivo());
//        artefato.setDataCadastro(idArtefato.getObjetoRetorno().getDataCadastro());
//        artefato.setIdUsuario(idArtefato.getObjetoRetorno().getIdUsuario());
//
//        Transporte modeloDb = new Transporte();
//        modeloDb.setInformacoesVeiculoTransporte(novo.getInformacoesVeiculoTransporte());
//        modeloDb.setInformacoesCondutorTransporte(novo.getInformacoesCondutorTransporte());
//        modeloDb.setQtdAssentosPreenchidosTransporte(novo.getQtdAssentosPreenchidosTransporte());
//        modeloDb.setQtdAssentosTotalTransporte(novo.getQtdAssentosTotalTransporte());
//        modeloDb.setCidadeTransporte(novo.getCidadeTransporte());
//        modeloDb.setPeriodoTransporte(novo.getPeriodoTransporte());
//        modeloDb.setArtefato(artefato);
//
//        //Salvando
//        Transporte transporteSalva = transporteRepository.save(modeloDb);
//
//        // Mapeia de entidade para dto
//        TransporteResponse transporteResponse = new TransporteResponse(transporteSalva);
//        return new ResponseBase<>(transporteResponse);
//    }

//    public TransporteResponse atualizarTransporte(Long idTransporte, TransporteUpdateRequest transporteUpdateRequest){
//
//        var comidaEncontrada = transporteRepository.findById(idTransporte);
//
//        if(comidaEncontrada.isEmpty()){
//            throw new TransporteNaoEncontradoException("Transporte não encontrado.");
//        }
//
//        var transporte = comidaEncontrada.get();
//        transporte.setInformacoesVeiculoTransporte(transporteUpdateRequest.getInformacoesVeiculoTransporte());
//        transporte.setInformacoesCondutorTransporte(transporteUpdateRequest.getInformacoesCondutorTransporte());
//        transporte.setQtdAssentosPreenchidosTransporte(transporteUpdateRequest.getQtdAssentosPreenchidosTransporte());
//        transporte.setQtdAssentosTotalTransporte(transporteUpdateRequest.getQtdAssentosTotalTransporte());
//        transporte.setCidadeTransporte(transporteUpdateRequest.getCidadeTransporte());
//        transporte.setPeriodoTransporte(transporteUpdateRequest.getPeriodoTransporte());
//
//        var transporteSalvo = transporteRepository.save(transporte);
//
//        return new TransporteResponse(
//                transporte.getIdArtefato(),
//                transporte.getInformacoesVeiculoTransporte(),
//                transporte.getInformacoesCondutorTransporte(),
//                transporte.getQtdAssentosPreenchidosTransporte(),
//                transporte.getQtdAssentosTotalTransporte(),
//                transporte.getCidadeTransporte(),
//                transporte.getPeriodoTransporte(),
//                transporte.getArtefato().getDescricaoArtefato()
//                //transporte.getListaImagensTransporte(),
//        );
//    }

//    public TransporteResponse deletar(Long idTransporte) {
//        var transporteEncontrada = transporteRepository.findById(idTransporte);
//
//        if (transporteEncontrada.isEmpty()) {
//            throw new TransporteNaoEncontradoException("Transporte não encontrado.");
//        }
//
//        var transporte = transporteEncontrada.get();
//        transporteRepository.delete(transporte);
//
//        return new TransporteResponse(
//                transporte.getIdArtefato(),
//                transporte.getInformacoesVeiculoTransporte(),
//                transporte.getInformacoesCondutorTransporte(),
//                transporte.getQtdAssentosPreenchidosTransporte(),
//                transporte.getQtdAssentosTotalTransporte(),
//                transporte.getCidadeTransporte(),
//                transporte.getPeriodoTransporte(),
//                transporte.getArtefato().getDescricaoArtefato()
//                //transporte.getListaImagensTransporte(),
//        );
//    }
}

