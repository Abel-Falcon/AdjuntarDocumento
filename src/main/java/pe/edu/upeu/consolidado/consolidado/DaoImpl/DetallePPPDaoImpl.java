package pe.edu.upeu.consolidado.consolidado.DaoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.edu.upeu.consolidado.consolidado.Dao.DetallePPPDao;
import pe.edu.upeu.consolidado.consolidado.entity.Detalle_PPP;
import pe.edu.upeu.consolidado.consolidado.repository.DetallePPPRepository;

@Component
public class DetallePPPDaoImpl implements DetallePPPDao{
	@Autowired
	private DetallePPPRepository detallePPPRepository;
	@Override
	public Detalle_PPP create(Detalle_PPP d) {
		// TODO Auto-generated method stub
		return detallePPPRepository.save(d);
	}

	@Override
	public Detalle_PPP update(Detalle_PPP d) {
		// TODO Auto-generated method stub
		return detallePPPRepository.save(d);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		detallePPPRepository.deleteById(id);
	}

	@Override
	public Optional<Detalle_PPP> read(Long id) {
		// TODO Auto-generated method stub
		return detallePPPRepository.findById(id);
	}

	@Override
	public List<Detalle_PPP> readAll() {
		// TODO Auto-generated method stub
		return detallePPPRepository.findAll();
	}

}
