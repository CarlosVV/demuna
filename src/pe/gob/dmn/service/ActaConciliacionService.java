package pe.gob.dmn.service;

import java.util.List;

import pe.gob.dmn.model.ExpedientesConciliacione;



public interface ActaConciliacionService {

	public List<ExpedientesConciliacione> cargarDatosConciliacion(
			Long expediente);

	public void eliminarConciliacion(Long kyActa);

	public void registrarConciliacion(ExpedientesConciliacione conc);
	
}
