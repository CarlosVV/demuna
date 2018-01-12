package pe.gob.dmn.service;

import java.util.List;

import pe.gob.dmn.model.ExpedientesMateria;

public interface ExpedienteMateriaService {
 
	public ExpedientesMateria obtenerMateria(Long kyMateria);
	
	public List<ExpedientesMateria> obtenerPorExpediente(Integer expediente);

	public void registrarExpedienteMateria(ExpedientesMateria em);	
	
	public void eliminarMateria(Long kyMateria);
	
}
