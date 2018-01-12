package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.LocationDAO;
import pe.gob.dmn.model.Location;
import pe.gob.dmn.service.LocationService;


/**
 * @author PEDROJSABA
 *
 */
@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationDAO locationDAO;
	
	@Override
	public List<Location> listarDepartamentos() {
		return locationDAO.listarDepartamentos();
	}
	
	@Override
	public List<Location> listarProvincias(String Departamento) {
		return locationDAO.listarProvincias(Departamento);
	}
	
	@Override
	public List<Location> listarDistritos(String Departamento, String Provincia) {
		return locationDAO.listarDistritos(Departamento, Provincia);
	}

}
