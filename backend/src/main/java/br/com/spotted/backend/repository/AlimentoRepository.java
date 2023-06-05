package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Alimento;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentoRepository extends PagingAndSortingRepository<Alimento, Long> {

}
