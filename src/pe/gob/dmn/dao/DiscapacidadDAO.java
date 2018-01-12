package pe.gob.dmn.dao;

import java.util.List;

import pe.gob.dmn.model.Discapacidad;

public interface DiscapacidadDAO {

	public List<Discapacidad> listarDiscapacidades();

	public List<Discapacidad> listarDiscapacidades(Integer Estado);
	
	public void registrarDiscapacidad(Discapacidad dis);

	public void eliminarDiscapacidad(Integer kyDiscapacidad);
	
}
