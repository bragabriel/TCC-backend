package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                    "WHERE (nomeUsuario = :nome) " +
                    "AND (senhaUsuario = :senha)"
    )
    Usuario login(@Param("nome") String nomeUsuario,
                        @Param("senha") String senhaUsuario);
}

