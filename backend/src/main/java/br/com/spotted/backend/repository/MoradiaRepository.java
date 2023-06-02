package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.Moradia;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoradiaRepository extends PagingAndSortingRepository<Moradia, Long> {

}