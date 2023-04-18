package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Estagio;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstagioRepository extends PagingAndSortingRepository<Estagio, Long> {

}

