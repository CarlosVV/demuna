package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.DemunaDAO;
import pe.gob.dmn.model.Demuna;
import pe.gob.dmn.service.DemunaService;

@Service
public class DemunaServiceImpl implements DemunaService {

	@Autowired
	private DemunaDAO demunaDAO;
	
	@Override
	public Demuna obtener(Integer kyDemuna) {
		return demunaDAO.obtener(kyDemuna);
	}
	
	@Override
	public List<Demuna> listarDemunas() {
		return demunaDAO.listarDemunas();
	}
	
	@Override
	public List<Demuna> listarDemunas(Integer kyTipoDemuna) {
		return demunaDAO.listarDemunas(kyTipoDemuna);
	}
	
	@Override
	public void registrarDemuna(Demuna dem) {
		demunaDAO.registrarDemuna(dem);
	}
	
	@Override
	public void actualizarImagen(Demuna dem) {
		demunaDAO.actualizarImagen(dem);
	}

	@Override
	public void eliminarDemuna(Integer kyDemuna) {
		demunaDAO.eliminarDemuna(kyDemuna);
	}
}
