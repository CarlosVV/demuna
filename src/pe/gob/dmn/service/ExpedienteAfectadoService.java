package pe.gob.dmn.service;

import java.util.List;

import pe.gob.dmn.model.ExpedientesAfectado;

public interface ExpedienteAfectadoService {
 
	public ExpedientesAfectado obtenerAfectado(Long kyAfectado);
	
	public List<ExpedientesAfectado> obtenerPorExpediente(Integer expediente);

	public void registrarExpedienteAfectado(ExpedientesAfectado ea);
	
	public void actualizarExpedienteAfectado(ExpedientesAfectado ea);
	
	public void eliminarAfectado(Long kyAfectado);
	
}
