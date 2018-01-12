package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.MateriaDAO;
import pe.gob.dmn.model.Materia;
import pe.gob.dmn.service.MateriaService;


/**
 * @author ECAMPOS
 *
 */
@Service
public class MateriaServiceImpl implements MateriaService {

	@Autowired
	private MateriaDAO materiasDAO;
	
	@Override
	public List<Materia> listarMaterias() {
		return materiasDAO.listarMaterias();
	}
	
	@Override
	public List<Materia> listarMaterias(Integer estado) {
		return materiasDAO.listarMaterias(estado);
	}

	@Override
	public Materia obtenerMateria(Integer kyMateria) {
		return materiasDAO.obtenerMateria(kyMateria);
	}

	@Override
	public void registrarMateria(Materia mat) {
		materiasDAO.registrarMateria(mat);
	}

	@Override
	public void eliminarMateria(Integer kymateria) {
		materiasDAO.eliminarMateria(kymateria);
	}

}
