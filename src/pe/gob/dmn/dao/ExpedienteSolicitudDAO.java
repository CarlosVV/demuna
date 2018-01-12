package pe.gob.dmn.dao;

import pe.gob.dmn.model.ExpedientesAfectado;
import pe.gob.dmn.model.ExpedientesSolicitud;
import pe.gob.dmn.model.Tipoatencion;

import java.util.List;

public interface ExpedienteSolicitudDAO {	

	public List<ExpedientesSolicitud> cargarSolicitud(Integer expediente);

	public List<ExpedientesAfectado> cargarAfectado(Integer expediente);

	void registrarSolicitud(ExpedientesSolicitud exp);

	public List<Tipoatencion> cargarTipoAtencion();

	public void eliminarSolicitud(Integer kySolicitud);

	public ExpedientesSolicitud obtenerPorExpedienteSolicitud(Integer kysolicitud);
	
}
