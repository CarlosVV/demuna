package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.ExpedienteSolicitudDAO;
import pe.gob.dmn.model.ExpedientesAfectado;
import pe.gob.dmn.model.ExpedientesSolicitud;
import pe.gob.dmn.model.Tipoatencion;
import pe.gob.dmn.service.ExpedienteSolicitudService;

@Service
public class ExpedienteSolicitudServiceImpl implements ExpedienteSolicitudService {

	@Autowired
	private ExpedienteSolicitudDAO expedienteSolicitudDAO;
	
	@Override
	public ExpedientesSolicitud obtenerPorExpedienteSolicitud(Integer kysolicitud) {
		return expedienteSolicitudDAO.obtenerPorExpedienteSolicitud(kysolicitud);
	}

	@Override
	public List<ExpedientesSolicitud> cargarSolicitud(Integer expediente) {
		return expedienteSolicitudDAO.cargarSolicitud(expediente);
	}

	@Override
	public List<ExpedientesAfectado> cargarAfectado(Integer expediente) {
		return expedienteSolicitudDAO.cargarAfectado(expediente);
	}

	@Override
	public List<Tipoatencion> cargarTipoAtencion() {
		return expedienteSolicitudDAO.cargarTipoAtencion();
	}

	@Override
	public void registrarSolicitud(ExpedientesSolicitud exp) {
		expedienteSolicitudDAO.registrarSolicitud(exp);
	}
	
	@Override
	public void eliminarSolicitud(Integer kysolicitud) {
		expedienteSolicitudDAO.eliminarSolicitud(kysolicitud);
	}

}
