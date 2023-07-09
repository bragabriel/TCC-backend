package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Emprego;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpregoRepository extends PagingAndSortingRepository<Emprego, Long> {

}

