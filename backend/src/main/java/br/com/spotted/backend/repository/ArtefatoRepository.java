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

    @Query("SELECT a FROM Artefato a LEFT JOIN a.alimento al WHERE a.tipoArtefato = :tipo AND a.ativo = :ativo")
    Page<Artefato> findAllWithAlimento(@Param("tipo") String tipo, Pageable pageable, @Param("ativo") Boolean ativo);

    @Query("SELECT a FROM Artefato a LEFT JOIN a.emprego em WHERE a.tipoArtefato = :tipo AND a.ativo = :ativo")
    Page<Artefato> findAllWithEmprego(@Param("tipo") String tipo, Pageable pageable, @Param("ativo") Boolean ativo);

    @Query("SELECT a FROM Artefato a LEFT JOIN a.festa fe WHERE a.tipoArtefato = :tipo AND a.ativo = :ativo")
    Page<Artefato> findAllWithFesta(@Param("tipo") String tipo, Pageable pageable, @Param("ativo") Boolean ativo);

    @Query("SELECT a FROM Artefato a LEFT JOIN a.moradia mo WHERE a.tipoArtefato = :tipo AND a.ativo = :ativo")
    Page<Artefato> findAllWithMoradia(@Param("tipo") String tipo, Pageable pageable, @Param("ativo") Boolean ativo);

    @Query("SELECT a FROM Artefato a LEFT JOIN a.objeto ob WHERE a.tipoArtefato = :tipo AND a.ativo = :ativo")
    Page<Artefato> findAllWithObjeto(@Param("tipo") String tipo, Pageable pageable, @Param("ativo") Boolean ativo);

    @Query("SELECT a FROM Artefato a LEFT JOIN a.transporte tr WHERE a.tipoArtefato = :tipo AND a.ativo = :ativo")
    Page<Artefato> findAllWithTransporte(@Param("tipo") String tipo, Pageable pageable, @Param("ativo") Boolean ativo);
}