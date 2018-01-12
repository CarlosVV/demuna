package pe.gob.dmn.dao;

import java.util.List;

import pe.gob.dmn.model.ExpedientesVerificacion;

public interface ExpedienteVerificacionDAO {
 
	public ExpedientesVerificacion obtenerVerificacion(Long kyVerificacion);
	
	public List<ExpedientesVerificacion> obtenerPorExpediente(Integer expediente);

	public void registrarExpedienteVerificacion(ExpedientesVerificacion ev);		
	
	public void eliminarVerificacion(Long kyVerificacion);
}
