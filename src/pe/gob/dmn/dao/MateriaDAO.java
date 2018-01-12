package pe.gob.dmn.dao;

import java.util.List;

import pe.gob.dmn.model.Materia;

public interface MateriaDAO {

	public List<Materia> listarMaterias();	
	
	public Materia obtenerMateria(Integer kyMateria);

	public void registrarMateria(Materia mat);

	public void eliminarMateria(Integer kymateria);

	public List<Materia> listarMaterias(Integer estado);
	
}
