package br.com.spotted.backend.repository.images;

import br.com.spotted.backend.domain.entity.image.AlimentoImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageAlimentoRepository extends JpaRepository<AlimentoImage, Long> {

}

