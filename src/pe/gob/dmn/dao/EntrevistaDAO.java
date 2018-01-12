package pe.gob.dmn.dao;

import java.util.List;
import pe.gob.dmn.model.ExpedientesEntrevista;

public interface EntrevistaDAO {

	List<ExpedientesEntrevista> cargarEntrevista(Integer expediente);

	void registrarEntrevista(ExpedientesEntrevista ent);

	void eliminarEntrevista(Integer kyEntrevista);
	
}
