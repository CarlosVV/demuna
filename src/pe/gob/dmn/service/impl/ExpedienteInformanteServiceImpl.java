package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.ExpedienteInformanteDAO;
import pe.gob.dmn.model.ExpedientesInformante;
import pe.gob.dmn.service.ExpedienteInformanteService;

/**
 * @author PEDROJSABA
 *
 */
@Service
public class ExpedienteInformanteServiceImpl implements ExpedienteInformanteService {

	@Autowired
	private ExpedienteInformanteDAO expedienteInformanteDAO;
	

	@Override
	public List<ExpedientesInformante> obtenerPorExpediente(Integer kyexpediente) {
		return expedienteInformanteDAO.obtenerPorExpediente(kyexpediente);
	}

	@Override
	public void registrarExpedienteInformante(ExpedientesInformante ei) {				
		expedienteInformanteDAO.registrarExpedienteInformante(ei);
	}
	
	@Override
	public void actualizarExpedienteInformante(ExpedientesInformante ei) {		
		expedienteInformanteDAO.actualizarExpedienteInformante(ei);
	}

	@Override
	public ExpedientesInformante obtenerInformante(Long kyInformante) {
		return expedienteInformanteDAO.obtenerInformante(kyInformante);
	}

	@Override
	public void eliminarInformante(Long kyInformante) {
		expedienteInformanteDAO.eliminarInformante(kyInformante);
	}
}
