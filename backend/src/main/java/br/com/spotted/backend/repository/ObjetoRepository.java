package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Objeto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetoRepository extends PagingAndSortingRepository<Objeto, Long> {

}
