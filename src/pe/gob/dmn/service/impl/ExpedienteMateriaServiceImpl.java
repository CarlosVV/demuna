package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.ExpedienteMateriaDAO;
import pe.gob.dmn.model.ExpedientesMateria;
import pe.gob.dmn.service.ExpedienteMateriaService;

/**
 * @author PEDROJSABA
 *
 */
@Service
public class ExpedienteMateriaServiceImpl implements ExpedienteMateriaService {

	@Autowired
	private ExpedienteMateriaDAO expedienteMateriaDAO;
	

	@Override
	public List<ExpedientesMateria> obtenerPorExpediente(Integer kyexpediente) {
		return expedienteMateriaDAO.obtenerPorExpediente(kyexpediente);
	}

	@Override
	public void registrarExpedienteMateria(ExpedientesMateria em) {
		expedienteMateriaDAO.registrarExpedienteMateria(em);
	}

	@Override
	public ExpedientesMateria obtenerMateria(Long kyMateria) {
		return expedienteMateriaDAO.obtenerMateria(kyMateria);
	}

	@Override
	public void eliminarMateria(Long kyMateria) {
		expedienteMateriaDAO.eliminarMateria(kyMateria);
	}
}
