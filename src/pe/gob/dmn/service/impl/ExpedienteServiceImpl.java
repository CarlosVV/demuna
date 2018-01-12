package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.ExpedienteDAO;
import pe.gob.dmn.model.Expediente;
import pe.gob.dmn.service.ExpedienteService;

/**
 * @author PEDROJSABA
 * 
 */
@Service
public class ExpedienteServiceImpl implements ExpedienteService {

	@Autowired
	private ExpedienteDAO expedienteDAO;

	@Override
	public List<Expediente> listarExpedientes(String campo, String valor) {
		return expedienteDAO.listarExpedientes(campo, valor);
	}

	@Override
	public List<Expediente> listarExpedientes(Integer KyDemuna) {
		return expedienteDAO.listarExpedientes(KyDemuna);
	}

	@Override
	public void crearExpediente(Expediente expediente) {
		expedienteDAO.crearExpediente(expediente);
	}

	@Override
	public void actualizarExpediente(Expediente expediente) {
		expedienteDAO.actualizarExpediente(expediente);
	}
	
	@Override
	public void eliminarExpediente(Integer kyexpediente) {
		expedienteDAO.eliminarExpediente(kyexpediente);
	}

	@Override
	public Expediente obtenerExpediente(Integer kyExpediente) {
		return expedienteDAO.obtenerExpediente(kyExpediente);
	}

}
