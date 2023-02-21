package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Festa;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FestaRepository extends PagingAndSortingRepository<Festa, Long> {

}
