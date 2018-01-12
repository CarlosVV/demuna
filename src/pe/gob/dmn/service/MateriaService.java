package pe.gob.dmn.service;

import java.util.List;

import pe.gob.dmn.model.Materia;

public interface MateriaService {

	public List<Materia> listarMaterias();
	
	public List<Materia> listarMaterias(Integer estado);

	public Materia obtenerMateria(Integer kyMateria);

	public void registrarMateria(Materia mat);

	public void eliminarMateria(Integer kymateria);
	
}
