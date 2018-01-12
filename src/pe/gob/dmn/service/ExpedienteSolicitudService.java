package pe.gob.dmn.service;

import java.util.List;

import pe.gob.dmn.model.ExpedientesAfectado;
import pe.gob.dmn.model.ExpedientesSolicitud;
import pe.gob.dmn.model.Tipoatencion;

public interface ExpedienteSolicitudService {	

	public List<ExpedientesSolicitud> cargarSolicitud(Integer expediente);

	public List<ExpedientesAfectado> cargarAfectado(Integer expediente);

	public List<Tipoatencion> cargarTipoAtencion();

	public void registrarSolicitud(ExpedientesSolicitud exp);

	public ExpedientesSolicitud obtenerPorExpedienteSolicitud(Integer kysolicitud);

	public void eliminarSolicitud(Integer kysolicitud);
	
}
