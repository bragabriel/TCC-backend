package br.com.spotted.backend.repository.images;

import br.com.spotted.backend.domain.entity.image.EmpregoImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageEstagioRepository extends JpaRepository<EmpregoImage, Long> {

}