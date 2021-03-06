package pe.gob.dmn.service;

import java.util.List;

import pe.gob.dmn.model.Expediente;

public interface ExpedienteService {

	public List<Expediente> listarExpedientes(String campo, String valor);
	
	public List<Expediente> listarExpedientes(Integer KyDemuna);
	
	public void crearExpediente(Expediente expediente);
	
	public void actualizarExpediente(Expediente expediente);
	
	public void eliminarExpediente(Integer kyexpediente);

	public Expediente obtenerExpediente(Integer kyExpediente);	
	
}
