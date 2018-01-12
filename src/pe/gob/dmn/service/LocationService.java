package pe.gob.dmn.service;

import java.util.List;

import pe.gob.dmn.model.Location;

public interface LocationService {

	public List<Location> listarDepartamentos();
	
	public List<Location> listarProvincias(String Departamento);
	
	public List<Location> listarDistritos(String Departamento, String Provincia);
	
}
