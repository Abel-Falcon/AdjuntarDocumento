package pe.edu.upeu.consolidado.consolidado.Dao;

import java.util.List;
import java.util.Optional;

import pe.edu.upeu.consolidado.consolidado.entity.Detalle_PPP;

public interface DetallePPPDao {
	Detalle_PPP create(Detalle_PPP d);
	Detalle_PPP update(Detalle_PPP d);
	void delete(Long id);
	Optional<Detalle_PPP> read(Long id);
	List<Detalle_PPP> readAll();
}
