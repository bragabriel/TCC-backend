package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Transporte;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransporteRepository extends PagingAndSortingRepository<Transporte, Long> {
}
