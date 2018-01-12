package pe.gob.dmn.service;

import java.util.List;
import pe.gob.dmn.model.ExpedientesEntrevista;


public interface EntrevistaService {

	public void eliminarEntrevista(Integer kyEntrevista);

	public List<ExpedientesEntrevista> cargarEntrevista(Integer expediente);

	public void registrarEntrevista(ExpedientesEntrevista ent);
	
}
