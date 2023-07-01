package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Artefato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtefatoRepository extends PagingAndSortingRepository<Artefato, Long> {

    @Query("SELECT a FROM Artefato a LEFT JOIN a.alimento WHERE a.tipoArtefato = :tipo")
    Page<Artefato> findAllWithAlimento(@Param("tipo") String tipo, Pageable pageable);

    @Query("SELECT a FROM Artefato a LEFT JOIN a.transporte WHERE a.tipoArtefato = :tipo")
    Page<Artefato> findAllWithTransporte(@Param("tipo") String tipo, Pageable pageable);
}


