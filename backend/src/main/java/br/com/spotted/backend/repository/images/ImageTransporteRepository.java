package br.com.spotted.backend.repository.images;


import br.com.spotted.backend.domain.entity.image.TransporteImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageTransporteRepository extends JpaRepository<TransporteImage, Long> {

}