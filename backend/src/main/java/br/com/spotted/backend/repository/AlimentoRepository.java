package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Alimento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlimentoRepository extends PagingAndSortingRepository<Alimento, Long> {

    @Query("SELECT a FROM Alimento a INNER JOIN a.artefato ar WHERE ar.ativo = true")
    List<Alimento> findAllAlimentosWithArtefatoAtivo();
}