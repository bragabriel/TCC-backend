package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Crush;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrushRepository extends PagingAndSortingRepository<Crush, Long> {
}
