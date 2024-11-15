package pe.edu.upeu.consolidado.consolidado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upeu.consolidado.consolidado.Dao.DetallePPPDao;
import pe.edu.upeu.consolidado.consolidado.entity.Detalle_PPP;

@Service
public class DetallePPPServiceDaoImpl implements DetallePPPService{
	@Autowired
	private DetallePPPDao detallePPPDao;
	@Override
	public Detalle_PPP create(Detalle_PPP d) {
		// TODO Auto-generated method stub
		return detallePPPDao.create(d);
	}

	@Override
	public Detalle_PPP update(Detalle_PPP d) {
		// TODO Auto-generated method stub
		return detallePPPDao.update(d);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		detallePPPDao.delete(id);
	}

	@Override
	public Optional<Detalle_PPP> read(Long id) {
		// TODO Auto-generated method stub
		return detallePPPDao.read(id);
	}

	@Override
	public List<Detalle_PPP> readAll() {
		// TODO Auto-generated method stub
		return detallePPPDao.readAll();
	}

}
