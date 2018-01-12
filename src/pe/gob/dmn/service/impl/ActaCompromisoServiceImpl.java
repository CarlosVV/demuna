package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.dmn.dao.ActaCompromisoDAO;
import pe.gob.dmn.model.ExpedientesCompromiso;
import pe.gob.dmn.service.ActaCompromisoService;

@Service
public class ActaCompromisoServiceImpl implements ActaCompromisoService {

	@Autowired
	private ActaCompromisoDAO actaCompromisoDAO;

	@Override
	public List<ExpedientesCompromiso> cargarDatosCompromiso(Long expediente) {		
		return actaCompromisoDAO.cargarDatosCompromiso(expediente);
	}

	@Override
	public void eliminarCompromiso(Long kyCompromiso) {
		actaCompromisoDAO.eliminarCompromiso(kyCompromiso);		
	}

	@Override
	public void registrarCompromiso(ExpedientesCompromiso comp) {
		actaCompromisoDAO.registrarCompromiso(comp);		
	}
}
