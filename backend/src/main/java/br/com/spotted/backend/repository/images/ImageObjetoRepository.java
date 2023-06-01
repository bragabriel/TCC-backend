package br.com.spotted.backend.repository.images;

import br.com.spotted.backend.domain.entity.image.ObjetoImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageObjetoRepository extends JpaRepository<ObjetoImage, Long> {

}