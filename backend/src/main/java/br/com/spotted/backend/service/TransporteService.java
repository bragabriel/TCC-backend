package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoInactiveRequest;
import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import br.com.spotted.backend.domain.dto.Transporte.TransporteCreateRequest;
import br.com.spotted.backend.domain.dto.Transporte.TransporteResponse;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.dto.Transporte.TransporteUpdateRequest;
import br.com.spotted.backend.domain.entity.Transporte;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.exception.TransporteNotFoundException;
import br.com.spotted.backend.repository.TransporteRepository;
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
public class TransporteService {

    private final TransporteRepository transporteRepository;
    @Autowired
    private final ArtefatoService artefatoService;

    public ResponseBase<List<TransporteResponse>> pesquisar() {
        Iterable<Transporte> transportes = transporteRepository.findAll();
        List<TransporteResponse> transporteResponses = new ArrayList<>();

        for (Transporte transporte : transportes) {
            transporteResponses.add(new TransporteResponse(transporte));
        }

        return new ResponseBase<>(transporteResponses);
    }

    public ResponseBase<Page<TransporteResponse>> pesquisarPaginado(PaginatedSearchRequest searchRequest) {

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

        ResponseBase<ArtefatoResponse> artefatoSalvo = artefatoService.cadastrar(novo.getArtefato());

        Artefato artefato = Artefato.builder()
                .tituloArtefato(artefatoSalvo.getObjetoRetorno().getTituloArtefato())
                .descricaoArtefato(artefatoSalvo.getObjetoRetorno().getDescricaoArtefato())
                .tipoArtefato(artefatoSalvo.getObjetoRetorno().getTipoArtefato())
                .ativo(artefatoSalvo.getObjetoRetorno().getAtivo())
                .dataCadastro(artefatoSalvo.getObjetoRetorno().getDataCadastro())
                .idUsuario(artefatoSalvo.getObjetoRetorno().getIdUsuario())
                .build();

        Transporte modeloDb = Transporte.builder()
                .idArtefato(artefatoSalvo.getObjetoRetorno().getIdArtefato())
                .informacoesCondutorTransporte(novo.getInformacoesCondutorTransporte())
                .informacoesVeiculoTransporte(novo.getInformacoesVeiculoTransporte())
                .qtdAssentosTotalTransporte(novo.getQtdAssentosTotalTransporte())
                .qtdAssentosPreenchidosTransporte(novo.getQtdAssentosPreenchidosTransporte())
                .cidadeTransporte(novo.getCidadeTransporte())
                .periodoTransporte(novo.getPeriodoTransporte())
                .artefato(artefato)
                .build();

        Transporte transporteSalva = transporteRepository.save(modeloDb);

        TransporteResponse transporteResponse = new TransporteResponse(transporteSalva);
        return new ResponseBase<>(transporteResponse);
    }

    public TransporteResponse atualizarTransporte(Long idTransporte, TransporteUpdateRequest transporteUpdateRequest){

        Calendar cal = Calendar.getInstance();
        Date dataAtual = cal.getTime();

        var transporteEncontrado = transporteRepository.findById(idTransporte);
        if(transporteEncontrado.isEmpty()){
            throw new TransporteNotFoundException("Transporte não encontrado.");
        }

        Artefato artefato = new Artefato(transporteEncontrado.get().getArtefato());
        artefato.setTituloArtefato(transporteUpdateRequest.getTituloArtefato());
        artefato.setDescricaoArtefato(transporteUpdateRequest.getDescricaoArtefato());
        artefato.setDataAtualizacao(dataAtual);

        var transporte = transporteEncontrado.get();
        transporte.setInformacoesVeiculoTransporte(transporteUpdateRequest.getInformacoesVeiculoTransporte());
        transporte.setInformacoesCondutorTransporte(transporteUpdateRequest.getInformacoesCondutorTransporte());
        transporte.setQtdAssentosPreenchidosTransporte(transporteUpdateRequest.getQtdAssentosPreenchidosTransporte());
        transporte.setQtdAssentosTotalTransporte(transporteUpdateRequest.getQtdAssentosTotalTransporte());
        transporte.setCidadeTransporte(transporteUpdateRequest.getCidadeTransporte());
        transporte.setPeriodoTransporte(transporteUpdateRequest.getPeriodoTransporte());
        transporte.setArtefato(artefato);
        transporteRepository.save(transporte);

        return new TransporteResponse(
                transporte.getIdArtefato(),
                transporte.getInformacoesVeiculoTransporte(),
                transporte.getInformacoesCondutorTransporte(),
                transporte.getQtdAssentosPreenchidosTransporte(),
                transporte.getQtdAssentosTotalTransporte(),
                transporte.getCidadeTransporte(),
                transporte.getPeriodoTransporte()
        );
    }

    public ResponseEntity inativarTransporte(Long idTransporte) {

        var transporteEncontrada = transporteRepository.findById(idTransporte);
        if (transporteEncontrada.isEmpty()) {
            throw new TransporteNotFoundException("Transporte não encontrada.");
        }

        Artefato artefato = new Artefato(transporteEncontrada.get().getArtefato());
        artefato.setAtivo(false);
        ArtefatoInactiveRequest artefatoInactiveRequest = new ArtefatoInactiveRequest(artefato);
        artefatoService.desativarArtefato(transporteEncontrada.get().getIdArtefato(), artefatoInactiveRequest);

        return ResponseEntity.ok().build();
    }
}

