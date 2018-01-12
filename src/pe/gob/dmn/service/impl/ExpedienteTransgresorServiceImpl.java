package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.ExpedienteTransgresorDAO;
import pe.gob.dmn.model.ExpedientesTransgresor;
import pe.gob.dmn.service.ExpedienteTransgresorService;

/**
 * @author PEDROJSABA
 *
 */
@Service
public class ExpedienteTransgresorServiceImpl implements ExpedienteTransgresorService {

	@Autowired
	private ExpedienteTransgresorDAO expedienteTransgresorDAO;
	

	@Override
	public List<ExpedientesTransgresor> obtenerPorExpediente(Integer kyexpediente) {
		return expedienteTransgresorDAO.obtenerPorExpediente(kyexpediente);
	}

	@Override
	public void registrarExpedienteTransgresor(ExpedientesTransgresor et) {	
		expedienteTransgresorDAO.registrarExpedienteTransgresor(et);
	}

	@Override
	public void actualizarExpedienteTransgresor(ExpedientesTransgresor et) {		
		expedienteTransgresorDAO.actualizarExpedienteTransgresor(et);
	}
	
	@Override
	public ExpedientesTransgresor obtenerPorTransgresor(Long kytransgresor) {
		return expedienteTransgresorDAO.obtenerPorTransgresor(kytransgresor);
	}

	@Override
	public void eliminarTransgresor(Long kytransgresor) {
		expedienteTransgresorDAO.eliminarTransgresor(kytransgresor);
	}
}
