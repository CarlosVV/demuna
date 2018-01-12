package pe.gob.dmn.dao;

import java.util.List;

import pe.gob.dmn.model.ExpedientesAfectado;

public interface ExpedienteAfectadoDAO {
 
	public ExpedientesAfectado obtenerAfectado(Long kyAfectado);
	
	public List<ExpedientesAfectado> obtenerPorExpediente(Integer expediente);

	public void registrarExpedienteAfectado(ExpedientesAfectado et);
	
	public void actualizarExpedienteAfectado(ExpedientesAfectado et);
	
	public void eliminarAfectado(Long kyAfectado);
}
