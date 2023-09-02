package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM Usuario " +
                    "WHERE (:nome IS NULL OR nomeUsuario = :nome)"
    )
    List<Usuario> listarComFiltroNativo(@Param("nome") String nome);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM Usuario " +
                    "WHERE (email_usuario = :email) " +
                    "AND (senha_usuario = :senha)"
    )
    Usuario login(@Param("email") String emailUsuario,
                        @Param("senha") String senhaUsuario);
    Optional<Usuario> findByEmailUsuario (String email);

    @Query("SELECT u FROM Usuario u JOIN u.listaArtefatos a WHERE u.idUsuario = :id AND a.ativo = true")
    Optional<Usuario> findByIdAndArtefatoAtivo(@Param("id") Long id);


    @Query(
            nativeQuery = true,
            value = "SELECT * FROM Usuario u " +
                    "INNER JOIN Artefato a " +
                    "ON a.id_usuario = u.id_usuario " +
                    "WHERE (u.id_usuario = :id) " +
                    "AND (a.ativo = 1)"
    )
    Optional<Usuario> teste(@Param("id") Long id);
}

