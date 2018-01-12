package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.ExpedienteVerificacionDAO;
import pe.gob.dmn.model.ExpedientesVerificacion;
import pe.gob.dmn.service.ExpedienteVerificacionService;

/**
 * @author PEDROJSABA
 *
 */
@Service
public class ExpedienteVerificacionServiceImpl implements ExpedienteVerificacionService {

	@Autowired
	private ExpedienteVerificacionDAO expedienteVerificacionDAO;
	

	@Override
	public List<ExpedientesVerificacion> obtenerPorExpediente(Integer kyexpediente) {
		return expedienteVerificacionDAO.obtenerPorExpediente(kyexpediente);
	}

	@Override
	public void registrarExpedienteVerificacion(ExpedientesVerificacion em) {
		expedienteVerificacionDAO.registrarExpedienteVerificacion(em);
	}

	@Override
	public ExpedientesVerificacion obtenerVerificacion(Long kyVerificacion) {
		return expedienteVerificacionDAO.obtenerVerificacion(kyVerificacion);
	}

	@Override
	public void eliminarVerificacion(Long kyVerificacion) {
		expedienteVerificacionDAO.eliminarVerificacion(kyVerificacion);
	}
}
