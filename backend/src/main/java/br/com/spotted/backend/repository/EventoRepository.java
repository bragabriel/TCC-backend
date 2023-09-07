package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Evento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends PagingAndSortingRepository<Evento, Long> {

    @Query("SELECT a FROM Evento a INNER JOIN a.artefato ar WHERE ar.ativo = true")
    List<Evento> findAllEventosWithArtefatoAtivo();
}
