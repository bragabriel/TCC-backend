package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Ape;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApeRepository extends PagingAndSortingRepository<Ape, Long> {

}
