package pe.gob.dmn.dao;

import java.util.List;
import pe.gob.dmn.model.ExpedientesCompromiso;

public interface ActaCompromisoDAO {

	List<ExpedientesCompromiso> cargarDatosCompromiso(Long expediente);

	void registrarCompromiso(ExpedientesCompromiso comp);

	void eliminarCompromiso(Long kyCompromiso);	
}
