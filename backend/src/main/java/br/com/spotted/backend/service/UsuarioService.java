package br.com.spotted.backend.service;


import br.com.spotted.backend.domain.dto.Criptografia;
import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioCreateRequest;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioResponse;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioUpdateRequest_NomeSobrenome;
import br.com.spotted.backend.domain.entity.Usuario;
import br.com.spotted.backend.exception.UsuarioNaoEncontradoException;
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
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public ResponseBase<UsuarioResponse> cadastrar(UsuarioCreateRequest novo) {

        Usuario modeloDb = new Usuario();
        modeloDb.setNomeUsuario(novo.getNome());
        modeloDb.setSobrenomeUsuario(novo.getSobrenome());
        modeloDb.setEmailUsuario(novo.getEmail());
        modeloDb.setSenhaUsuario(Criptografia.encriptografar(novo.getSenha()));
        modeloDb.setTelefoneUsuario(novo.getTelefone());
        modeloDb.setDataNascimento(novo.getDataNascimento());
        modeloDb.setImagemUsuario(novo.getImagemUsuario());

        //Salvando
        Usuario usuarioSalvo = usuarioRepository.save(modeloDb);

        // Mapeia de entidade para dto
        UsuarioResponse usuarioResponse = new UsuarioResponse(usuarioSalvo);
        return new ResponseBase<>(usuarioResponse);
    }

    public ResponseBase<Page<UsuarioResponse>> pesquisar(PaginatedSearchRequest searchRequest) {

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
        Page<Usuario> responsavelPage = usuarioRepository.findAll(pageRequest);

        // Mapeia da entidade(Customer) para o DTO(CustomerResponse)
        Page<UsuarioResponse> responsavelResponsePage = responsavelPage.map(UsuarioResponse::new);
        return new ResponseBase<>(responsavelResponsePage);
    }

    public ResponseBase<UsuarioResponse> pesquisarPorId(Long id) {
        // Consulta o repositorio para procurar por um custumer pelo id
        Optional<Usuario> responsavelOptional = usuarioRepository.findById(id);

        // Verifica se o custimer foi encontrado, caso o contratrio retorna um erro
        Usuario usuario = responsavelOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsável não encontrado"));

        // Mapeia de entidade para dto
        UsuarioResponse usuarioResponse = new UsuarioResponse(usuario);

        return new ResponseBase<>(usuarioResponse);
    }

    public UsuarioResponse deletar(Long idUsuario) throws UsuarioNaoEncontradoException {
        var usuarioEncontrado = usuarioRepository.findById(idUsuario);

        if (usuarioEncontrado.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Tarefa não encontrada.");
        }

        var usuario = usuarioEncontrado.get();
        usuarioRepository.delete(usuario);

        return new UsuarioResponse(
                usuario.getIdUsuario(),
                usuario.getNomeUsuario(),
                usuario.getSobrenomeUsuario(),
                usuario.getEmailUsuario(),
                usuario.getSenhaUsuario(),
                usuario.getTelefoneUsuario(),
                usuario.getDataNascimento(),
                usuario.getDescricaoUsuario(),
                usuario.getImagemUsuario()
        );
    }

    public UsuarioResponse logar(String nome, String senha) {

        var senhaCriptografada = Criptografia.encriptografar(senha);

        // Consulta o repositorio para procurar por um custumer pelo nome e senha
        var retorno = usuarioRepository.login(nome, senhaCriptografada);

        if (retorno == null) {
            throw new UsuarioNaoEncontradoException("Usuário ou senha inválida.");
        }

        // Mapeia de entidade para dto
        UsuarioResponse usuarioResponse = new UsuarioResponse(retorno);

        return usuarioResponse;
    }

    public UsuarioResponse atualizarNomeSobrenome(Long idUsuario, UsuarioUpdateRequest_NomeSobrenome usuarioUpdateRequest){

        var usuarioEncontrado = usuarioRepository.findById(idUsuario);

        if(usuarioEncontrado.isEmpty()){
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }

        var usuario = usuarioEncontrado.get();
        usuario.setNomeUsuario(usuarioUpdateRequest.getNomeUsuario());
        usuario.setSobrenomeUsuario(usuarioUpdateRequest.getSobrenomeUsuario());

        var usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponse(
                usuarioSalvo.getIdUsuario(),
                usuarioSalvo.getNomeUsuario(),
                usuarioSalvo.getSobrenomeUsuario(),
                usuarioSalvo.getEmailUsuario(),
                usuarioSalvo.getSenhaUsuario(),
                usuarioSalvo.getTelefoneUsuario(),
                usuarioSalvo.getDataNascimento(),
                usuarioSalvo.getDescricaoUsuario(),
                usuarioSalvo.getImagemUsuario()
        );
    }
}
