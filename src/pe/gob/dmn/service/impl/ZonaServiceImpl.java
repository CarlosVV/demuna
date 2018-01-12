package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.ZonaDAO;
import pe.gob.dmn.model.Zona;
import pe.gob.dmn.service.ZonaService;


/**
 * @author PEDROJSABA
 *
 */
@Service
public class ZonaServiceImpl implements ZonaService {

	@Autowired
	private ZonaDAO zonaDAO;
	
	
	@Override
	public List<Zona> listarZonas(String Ubigeo) {
		return zonaDAO.listarZonas(Ubigeo);
	}	

}
