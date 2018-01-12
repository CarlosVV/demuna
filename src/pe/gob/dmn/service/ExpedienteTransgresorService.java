package pe.gob.dmn.service;

import java.util.List;

import pe.gob.dmn.model.ExpedientesTransgresor;

public interface ExpedienteTransgresorService {
 
	public ExpedientesTransgresor obtenerPorTransgresor(Long kytransgresor);
	
	public List<ExpedientesTransgresor> obtenerPorExpediente(Integer expediente);

	public void registrarExpedienteTransgresor(ExpedientesTransgresor et);
	
	public void actualizarExpedienteTransgresor(ExpedientesTransgresor et);
	
	public void eliminarTransgresor(Long kytransgresor);
	
}
