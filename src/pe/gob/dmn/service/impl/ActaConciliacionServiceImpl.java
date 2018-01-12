package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.ActaConciliacionDAO;
import pe.gob.dmn.model.ExpedientesConciliacione;
import pe.gob.dmn.service.ActaConciliacionService;

@Service
public class ActaConciliacionServiceImpl implements ActaConciliacionService {

	@Autowired
	private ActaConciliacionDAO actaConciliacionDAO;

	@Override
	public List<ExpedientesConciliacione> cargarDatosConciliacion(
			Long expediente) {
		 
		return actaConciliacionDAO.cargarDatosConciliacion(expediente);
	}

	@Override
	public void eliminarConciliacion(Long kyActa) {
		actaConciliacionDAO.eliminarConciliacion(kyActa);
		
	}

	@Override
	public void registrarConciliacion(ExpedientesConciliacione conc) {
		actaConciliacionDAO.registrarConciliacion(conc);		
	}
	

}
