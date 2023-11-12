package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Objeto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjetoRepository extends PagingAndSortingRepository<Objeto, Long> {

    @Query("SELECT a FROM Objeto a INNER JOIN a.artefato ar WHERE ar.ativo = true")
    List<Objeto> findAllObjetosWithArtefatoAtivo();
}
