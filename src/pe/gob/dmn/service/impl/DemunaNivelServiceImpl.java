package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.DemunaNivelDAO;
import pe.gob.dmn.model.DemunaNivel;
import pe.gob.dmn.service.DemunaNivelService;

@Service
public class DemunaNivelServiceImpl implements DemunaNivelService {

	@Autowired
	private DemunaNivelDAO demunaNivelDAO;
	
	@Override
	public List<DemunaNivel> listaDemunaNivel() {
		return demunaNivelDAO.listaDemunaNivel();
	}

}
