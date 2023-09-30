package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Criptografia;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioCreateRequest;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioResponse;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioUpdateRequest_NomeSobrenome;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.Usuario;
import br.com.spotted.backend.exception.EmailDuplicadoException;
import br.com.spotted.backend.exception.TelefoneDuplicadoException;
import br.com.spotted.backend.exception.UsuarioNotFoundException;
import br.com.spotted.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public ResponseBase<List<UsuarioResponse>> pesquisar() {
        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponse> usuarioResponses = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            usuarioResponses.add(new UsuarioResponse(usuario));
        }

        return new ResponseBase<>(usuarioResponses);
    }

    public ResponseBase<Page<UsuarioResponse>> pesquisarPaginado(PaginatedSearchRequest searchRequest) {
        if (searchRequest.getPaginaAtual() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O indice da página atual deve começar em 1");
        }
        if (searchRequest.getQtdPorPagina() < 1 || searchRequest.getQtdPorPagina() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de itens por página deve ser entre 1 e 50 itens");
        }

        Page<Usuario> responsavelPage = usuarioRepository.findAll(searchRequest.parseToPageRequest());

        Page<UsuarioResponse> responsavelResponsePage = responsavelPage.map(UsuarioResponse::new);
        return new ResponseBase<>(responsavelResponsePage);
    }

    public ResponseBase<UsuarioResponse> pesquisarPorId(Long id) {
        Optional<Usuario> responsavelOptional = usuarioRepository.findById(id);

        Usuario usuario = responsavelOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        List<Artefato> artefatosAtivos = usuario.getListaArtefatos().stream()
                .filter(artefato -> artefato.getAtivo() == true)
                .collect(Collectors.toList());

        Usuario newUser = new Usuario(usuario);
        newUser.setListaArtefatos(artefatosAtivos);

        UsuarioResponse usuarioResponse = new UsuarioResponse(newUser);

        return new ResponseBase<>(usuarioResponse);
    }

    public ResponseBase<UsuarioResponse> cadastrar(UsuarioCreateRequest novo) {
        validateEmail(novo.getEmail());
        validateTelefone(novo.getTelefone());

        Usuario modeloDb = new Usuario();
        modeloDb.setNomeUsuario(novo.getNome());
        modeloDb.setSobrenomeUsuario(novo.getSobrenome());
        modeloDb.setEmailUsuario(novo.getEmail());
        modeloDb.setSenhaUsuario(Criptografia.encriptografar(novo.getSenha()));
        modeloDb.setTelefoneUsuario(novo.getTelefone());

        Usuario usuarioSalvo = usuarioRepository.save(modeloDb);

        UsuarioResponse usuarioResponse = new UsuarioResponse(usuarioSalvo);
        return new ResponseBase<>(usuarioResponse);
    }
    public UsuarioResponse deletar(Long idUsuario) throws UsuarioNotFoundException {
        var usuarioEncontrado = usuarioRepository.findById(idUsuario);

        if (usuarioEncontrado.isEmpty()) {
            throw new UsuarioNotFoundException("Tarefa não encontrada.");
        }

        var usuario = usuarioEncontrado.get();
        usuarioRepository.delete(usuario);

        return new UsuarioResponse(
                usuario
        );
    }

    public UsuarioResponse logar(String email, String senha) {
        var senhaCriptografada = Criptografia.encriptografar(senha);

        // Consulta o repositorio para procurar por um custumer pelo nome e senha
        var retorno = usuarioRepository.login(email, senhaCriptografada);
        if (retorno == null) {
            throw new UsuarioNotFoundException("Usuário ou senha inválida.");
        }

        UsuarioResponse usuarioResponse = new UsuarioResponse(retorno);

        return usuarioResponse;
    }

    public UsuarioResponse atualizarNomeSobrenome(Long idUsuario, UsuarioUpdateRequest_NomeSobrenome usuarioUpdateRequest){
        var usuarioEncontrado = usuarioRepository.findById(idUsuario);

        if(usuarioEncontrado.isEmpty()){
            throw new UsuarioNotFoundException("Usuário não encontrado");
        }

        var usuario = usuarioEncontrado.get();
        usuario.setNomeUsuario(usuarioUpdateRequest.getNomeUsuario());
        usuario.setSobrenomeUsuario(usuarioUpdateRequest.getSobrenomeUsuario());

        var usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponse(
                usuario
        );
    }

    private void validateEmail(String email) {
        if (usuarioRepository.findByEmailUsuario(email).isPresent()) {
            throw new EmailDuplicadoException("E-mail já cadastrado!");
        }
    }

    private void validateTelefone(String telefone) {
        if (usuarioRepository.findByTelefoneUsuario(telefone).isPresent()) {
            throw new TelefoneDuplicadoException("Telefone já cadastrado!");
        }
    }
}
