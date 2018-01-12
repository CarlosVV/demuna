package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.EntrevistaDAO;
import pe.gob.dmn.model.ExpedientesEntrevista;
import pe.gob.dmn.service.EntrevistaService;

@Service
public class EntrevistaServiceImpl implements EntrevistaService {

	@Autowired
	private EntrevistaDAO entrevistaDAO;

	@Override
	public void eliminarEntrevista(Integer kyEntrevista) {
		entrevistaDAO.eliminarEntrevista(kyEntrevista);		
	}

	@Override
	public List<ExpedientesEntrevista> cargarEntrevista(Integer expediente) {
		
		return entrevistaDAO.cargarEntrevista(expediente);
	}

	@Override
	public void registrarEntrevista(ExpedientesEntrevista ent) {
		entrevistaDAO.registrarEntrevista(ent);
		
	}

}
