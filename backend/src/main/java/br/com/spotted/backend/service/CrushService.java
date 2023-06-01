package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Crush.CrushUpdateRequest;
import br.com.spotted.backend.domain.dto.Crush.CrushCreateRequest;
import br.com.spotted.backend.domain.dto.Crush.CrushResponse;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Crush;
import br.com.spotted.backend.exception.CrushNaoEncontradoException;
import br.com.spotted.backend.exception.ComidaNaoEncontradaException;
import br.com.spotted.backend.repository.CrushRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrushService {

    private final CrushRepository crushRepository;
    private final UsuarioService usuarioService;

    public ResponseBase<Page<CrushResponse>> pesquisar(PaginatedSearchRequest searchRequest) {

        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Crush> crushPage = crushRepository.findAll(searchRequest.parseToPageRequest());

        Page<CrushResponse> apeResponsePage = crushPage.map(CrushResponse::new);
        return new ResponseBase<>(apeResponsePage);
    }

    public ResponseBase<CrushResponse> pesquisarPorId(Long id) {

        Optional<Crush> apeOptional = crushRepository.findById(id);

        Crush ape = apeOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Crush não encontrado."));

        CrushResponse apeResponse = new CrushResponse(ape);

        return new ResponseBase<>(apeResponse);
    }

    public ResponseBase<CrushResponse> cadastrar(CrushCreateRequest novo) {

        Crush modeloDb = new Crush();
        modeloDb.setTituloCrush(novo.getTituloCrush());
        modeloDb.setDescricaoCrush(novo.getDescricaoCrush());
        modeloDb.setCursoCrush(novo.getCursoCrush());
        modeloDb.setLocalizacaoCrush(novo.getLocalizacaoCrush());
        modeloDb.setPeriodoCrush(novo.getPeriodoCrush());
        modeloDb.setIdUsuario(novo.getIdUsuario());

        usuarioService.pesquisarPorId(novo.getIdUsuario());

        //Salvando
        Crush apeSalvo = crushRepository.save(modeloDb);

        //Mapeia de entidade para dto
        CrushResponse apeResponse = new CrushResponse(apeSalvo);
        return new ResponseBase<>(apeResponse);
    }

    public CrushResponse deletar(Long idCrush) {
        var crushEncontrado = crushRepository.findById(idCrush);

        if (crushEncontrado.isEmpty()) {
            throw new CrushNaoEncontradoException("Crush não encontrado.");
        }

        var crush = crushEncontrado.get();
        crushRepository.delete(crush);

        return new CrushResponse(
                crush.getIdCrush(),
                crush.getTituloCrush(),
                crush.getDescricaoCrush(),
                crush.getCursoCrush(),
                crush.getLocalizacaoCrush(),
                crush.getPeriodoCrush(),
                crush.getListaImagensCrush(),
                crush.getIdUsuario()
        );
    }

    public CrushResponse atualizarCrush(Long idCrush, CrushUpdateRequest crushUpdateRequest) {

        var crushEncontrado = crushRepository.findById(idCrush);

        if (crushEncontrado.isEmpty()) {
            throw new ComidaNaoEncontradaException("Crush não encontrado.");
        }

        var crush = crushEncontrado.get();

        crush.setTituloCrush(crushUpdateRequest.getTituloCrush());
        crush.setDescricaoCrush(crushUpdateRequest.getDescricaoCrush());
        crush.setCursoCrush(crushUpdateRequest.getCursoCrush());
        crush.setLocalizacaoCrush(crushUpdateRequest.getLocalizacaoCrush());
        crush.setPeriodoCrush(crushUpdateRequest.getPeriodoCrush());

        var crushSalvo = crushRepository.save(crush);

        return new CrushResponse(
                crush.getIdCrush(),
                crush.getTituloCrush(),
                crush.getDescricaoCrush(),
                crush.getCursoCrush(),
                crush.getLocalizacaoCrush(),
                crush.getPeriodoCrush(),
                crush.getListaImagensCrush(),
                crush.getIdUsuario()
        );
    }
}
