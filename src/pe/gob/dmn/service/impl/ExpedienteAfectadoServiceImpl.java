package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.ExpedienteAfectadoDAO;
import pe.gob.dmn.model.ExpedientesAfectado;
import pe.gob.dmn.service.ExpedienteAfectadoService;

/**
 * @author PEDROJSABA
 *
 */
@Service
public class ExpedienteAfectadoServiceImpl implements ExpedienteAfectadoService {

	@Autowired
	private ExpedienteAfectadoDAO expedienteAfectadoDAO;
	

	@Override
	public List<ExpedientesAfectado> obtenerPorExpediente(Integer kyexpediente) {
		return expedienteAfectadoDAO.obtenerPorExpediente(kyexpediente);
	}

	@Override
	public void registrarExpedienteAfectado(ExpedientesAfectado ea) {
		expedienteAfectadoDAO.registrarExpedienteAfectado(ea);
	}
	
	@Override
	public void actualizarExpedienteAfectado(ExpedientesAfectado ea) {
		expedienteAfectadoDAO.actualizarExpedienteAfectado(ea);
	}

	@Override
	public ExpedientesAfectado obtenerAfectado(Long kyAfectado) {
		return expedienteAfectadoDAO.obtenerAfectado(kyAfectado);
	}

	@Override
	public void eliminarAfectado(Long kyAfectado) {
		expedienteAfectadoDAO.eliminarAfectado(kyAfectado);
	}
}
