package pe.gob.dmn.service;

import java.util.List;

import pe.gob.dmn.model.ExpedientesCompromiso;



public interface ActaCompromisoService {

	public List<ExpedientesCompromiso> cargarDatosCompromiso(Long expediente);

	public void eliminarCompromiso(Long kyCompromiso);

	public void registrarCompromiso(ExpedientesCompromiso comp);
	
}
