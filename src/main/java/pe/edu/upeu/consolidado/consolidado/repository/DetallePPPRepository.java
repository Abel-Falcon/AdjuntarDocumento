package pe.edu.upeu.consolidado.consolidado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upeu.consolidado.consolidado.entity.Detalle_PPP;
@Repository
public interface DetallePPPRepository extends JpaRepository<Detalle_PPP, Long>{

}
