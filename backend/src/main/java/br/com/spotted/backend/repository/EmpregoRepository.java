package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Emprego;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpregoRepository extends PagingAndSortingRepository<Emprego, Long> {

    @Query("SELECT a FROM Emprego a INNER JOIN a.artefato ar WHERE ar.ativo = true")
    List<Emprego> findAllEmpregosWithArtefatoAtivo();
}

