package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Carona.CaronaCreateRequest;
import br.com.spotted.backend.domain.dto.Carona.CaronaResponse;
import br.com.spotted.backend.domain.dto.Carona.CaronaUpdateRequest;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Carona;
import br.com.spotted.backend.exception.ComidaNaoEncontradaException;
import br.com.spotted.backend.repository.CaronaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CaronaService {

    private final CaronaRepository caronaRepository;
    private final UsuarioService usuarioService;

    public ResponseBase<Page<CaronaResponse>> pesquisar(PaginatedSearchRequest searchRequest) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Carona> caronaPage = caronaRepository.findAll(searchRequest.parseToPageRequest());

        Page<CaronaResponse> caronaResponsePage = caronaPage.map(CaronaResponse::new);
        return new ResponseBase<>(caronaResponsePage);
    }

    public ResponseBase<CaronaResponse> pesquisarPorId(Long id) {
        Optional<Carona> caronaOptional = caronaRepository.findById(id);

        Carona carona = caronaOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carona não encontrada."));

        CaronaResponse caronaResponse = new CaronaResponse(carona);

        return new ResponseBase<>(caronaResponse);
    }

    public ResponseBase<CaronaResponse> cadastrar(CaronaCreateRequest novo) {

        Carona modeloDb = new Carona();
        modeloDb.setTituloCarona(novo.getTituloCarona());
        modeloDb.setDescricaoCarona(novo.getDescricaoCarona());
        modeloDb.setInformacoesVeiculoCarona(novo.getInformacoesVeiculoCarona());
        modeloDb.setInformacoesCondutorCarona(novo.getInformacoesCondutorCarona());
        modeloDb.setQtdAssentosAtualCarona(novo.getQtdAssentosAtualCarona());
        modeloDb.setQtdAssentosTotalCarona(novo.getQtdAssentosTotalCarona());
        modeloDb.setCidadeCarona(novo.getCidadeCarona());
        modeloDb.setPeriodoCarona(novo.getPeriodoCarona());
        modeloDb.setIdUsuario(novo.getIdUsuario());

        //Validação de usuário no banco de dados
        usuarioService.pesquisarPorId(novo.getIdUsuario());

        //Salvando
        Carona caronaSalva = caronaRepository.save(modeloDb);

        // Mapeia de entidade para dto
        CaronaResponse caronaResponse = new CaronaResponse(caronaSalva);
        return new ResponseBase<>(caronaResponse);
    }

    public CaronaResponse deletar(Long idCarona) {
        var caronaEncontrada = caronaRepository.findById(idCarona);

        if (caronaEncontrada.isEmpty()) {
            throw new ComidaNaoEncontradaException("Carona não encontrada.");
        }

        var carona = caronaEncontrada.get();
        caronaRepository.delete(carona);

        return new CaronaResponse(
                carona.getIdCarona(),
                carona.getTituloCarona(),
                carona.getDescricaoCarona(),
                carona.getInformacoesVeiculoCarona(),
                carona.getInformacoesCondutorCarona(),
                carona.getQtdAssentosAtualCarona(),
                carona.getQtdAssentosTotalCarona(),
                carona.getCidadeCarona(),
                carona.getPeriodoCarona(),
                carona.getListaImagensCarona(),
                carona.getIdUsuario()
        );
    }

    public CaronaResponse atualizarCarona(Long idCarona, CaronaUpdateRequest caronaUpdateRequest){

        var comidaEncontrada = caronaRepository.findById(idCarona);

        if(comidaEncontrada.isEmpty()){
            throw new ComidaNaoEncontradaException("Comida não encontrada.");
        }

        var carona = comidaEncontrada.get();
        carona.setTituloCarona(caronaUpdateRequest.getTituloCarona());
        carona.setDescricaoCarona(caronaUpdateRequest.getDescricaoCarona());
        carona.setInformacoesVeiculoCarona(caronaUpdateRequest.getInformacoesVeiculoCarona());
        carona.setInformacoesCondutorCarona(caronaUpdateRequest.getInformacoesCondutorCarona());
        carona.setQtdAssentosAtualCarona(caronaUpdateRequest.getQtdAssentosAtualCarona());
        carona.setQtdAssentosTotalCarona(caronaUpdateRequest.getQtdAssentosTotalCarona());
        carona.setCidadeCarona(caronaUpdateRequest.getCidadeCarona());
        carona.setPeriodoCarona(caronaUpdateRequest.getPeriodoCarona());

        var caronaSalva = caronaRepository.save(carona);

        return new CaronaResponse(
                carona.getIdCarona(),
                carona.getTituloCarona(),
                carona.getDescricaoCarona(),
                carona.getInformacoesVeiculoCarona(),
                carona.getInformacoesCondutorCarona(),
                carona.getQtdAssentosAtualCarona(),
                carona.getQtdAssentosTotalCarona(),
                carona.getCidadeCarona(),
                carona.getPeriodoCarona(),
                carona.getListaImagensCarona(),
                carona.getIdUsuario()
        );
    }
}

