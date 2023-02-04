package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Comida;
import br.com.spotted.backend.domain.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComidaRepository extends PagingAndSortingRepository<Comida, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM Comida " +
                    "WHERE (:comida IS NULL OR nomeComida = :nome)"
    )
    List<Comida> listarComFiltroNativo(@Param("nome") String nome);
}
