package pe.gob.dmn.dao;

import java.util.List;

import pe.gob.dmn.model.Location;

public interface LocationDAO {

	public List<Location> listarDepartamentos();
	
	public List<Location> listarProvincias(String Departamento);
	
	public List<Location> listarDistritos(String Departamento, String Provincias);
	
}
