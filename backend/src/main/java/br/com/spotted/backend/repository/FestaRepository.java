package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Alimento;
import br.com.spotted.backend.domain.entity.Festa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FestaRepository extends PagingAndSortingRepository<Festa, Long> {

    @Query("SELECT a FROM Festa a INNER JOIN a.artefato ar WHERE ar.ativo = true")
    List<Festa> findAllFestasWithArtefatoAtivo();
}
