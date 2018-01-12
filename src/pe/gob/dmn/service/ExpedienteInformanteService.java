package pe.gob.dmn.service;

import java.util.List;

import pe.gob.dmn.model.ExpedientesInformante;

public interface ExpedienteInformanteService {
 
	public ExpedientesInformante obtenerInformante(Long kyInformante);
	
	public List<ExpedientesInformante> obtenerPorExpediente(Integer expediente);

	public void registrarExpedienteInformante(ExpedientesInformante ei);
	
	public void actualizarExpedienteInformante(ExpedientesInformante ei);
	
	public void eliminarInformante(Long kyInformante);
	
}
