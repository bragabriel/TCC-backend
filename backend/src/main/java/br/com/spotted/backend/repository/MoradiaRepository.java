package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Moradia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoradiaRepository extends PagingAndSortingRepository<Moradia, Long> {

    @Query("SELECT a FROM Moradia a INNER JOIN a.artefato ar WHERE ar.ativo = true")
    List<Moradia> findAllMoradiasWithArtefatoAtivo();
}