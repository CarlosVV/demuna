package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.DiscapacidadDAO;
import pe.gob.dmn.model.Discapacidad;
import pe.gob.dmn.service.DiscapacidadService;


/**
 * @author PEDROJSABA
 *
 */
@Service
public class DiscapacidadServiceImpl implements DiscapacidadService {

	@Autowired
	private DiscapacidadDAO discapacidadDAO;
	
	@Override
	public List<Discapacidad> listarDiscapacidades() {
		return discapacidadDAO.listarDiscapacidades();
	}
	
	public List<Discapacidad> listarDiscapacidades(Integer Estado) {
		return discapacidadDAO.listarDiscapacidades(Estado);
	}

	@Override
	public void registrarDiscapacidad(Discapacidad dis) {
		discapacidadDAO.registrarDiscapacidad(dis);
	}

	@Override
	public void eliminarDiscapacidad(Integer kyDiscapacidad) {
		discapacidadDAO.eliminarDiscapacidad(kyDiscapacidad);
	}

}
