package pe.gob.dmn.service;

import java.util.List;

import pe.gob.dmn.model.Demuna;

public interface DemunaService {

	public Demuna obtener(Integer kyDemuna);
	
	public List<Demuna> listarDemunas();
	
	public List<Demuna> listarDemunas(Integer kyTipoDemuna);
	
	public void registrarDemuna(Demuna dem);	

	public void eliminarDemuna(Integer kyDemuna);

	public void actualizarImagen(Demuna dem);
}
