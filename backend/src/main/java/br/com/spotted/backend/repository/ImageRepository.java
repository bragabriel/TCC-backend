package br.com.spotted.backend.repository;

import br.com.spotted.backend.domain.entity.image.ComidaImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ComidaImage, Long> {

}

