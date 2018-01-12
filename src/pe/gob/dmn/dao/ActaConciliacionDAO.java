package pe.gob.dmn.dao;

import java.util.List;
import pe.gob.dmn.model.ExpedientesConciliacione;

public interface ActaConciliacionDAO {

	List<ExpedientesConciliacione> cargarDatosConciliacion(Long expediente);

	void registrarConciliacion(ExpedientesConciliacione conci);

	void eliminarConciliacion(Long kyActa);

	
}
