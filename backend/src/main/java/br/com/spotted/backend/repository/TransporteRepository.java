package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Transporte;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransporteRepository extends PagingAndSortingRepository<Transporte, Long> {

    @Query("SELECT a FROM Transporte a INNER JOIN a.artefato ar WHERE ar.ativo = true")
    List<Transporte> findAllTransportesWithArtefatoAtivo();
}
