package br.com.spotted.backend.repository.images;

import br.com.spotted.backend.domain.entity.image.ComidaImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageComidaRepository extends JpaRepository<ComidaImage, Long> {

}

