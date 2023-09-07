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

    @Query("SELECT a FROM Artefato a LEFT JOIN a.alimento al WHERE a.ativo = :ativo")
    Page<Artefato> findAllWithAlimento(Pageable pageable, @Param("ativo") Boolean ativo);

    @Query("SELECT a FROM Artefato a LEFT JOIN a.emprego em WHERE a.ativo = :ativo")
    Page<Artefato> findAllWithEmprego(Pageable pageable, @Param("ativo") Boolean ativo);

    @Query("SELECT a FROM Artefato a LEFT JOIN a.evento fe WHERE a.ativo = :ativo")
    Page<Artefato> findAllWithFesta(Pageable pageable, @Param("ativo") Boolean ativo);

    @Query("SELECT a FROM Artefato a LEFT JOIN a.moradia mo WHERE a.ativo = :ativo")
    Page<Artefato> findAllWithMoradia(Pageable pageable, @Param("ativo") Boolean ativo);

    @Query("SELECT a FROM Artefato a LEFT JOIN a.objeto ob WHERE a.ativo = :ativo")
    Page<Artefato> findAllWithObjeto(Pageable pageable, @Param("ativo") Boolean ativo);

    @Query("SELECT a FROM Artefato a LEFT JOIN a.transporte tr WHERE a.ativo = :ativo")
    Page<Artefato> findAllWithTransporte(Pageable pageable, @Param("ativo") Boolean ativo);
}