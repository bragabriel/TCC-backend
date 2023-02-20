package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Carona;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaronaRepository extends PagingAndSortingRepository<Carona, Long> {
}
