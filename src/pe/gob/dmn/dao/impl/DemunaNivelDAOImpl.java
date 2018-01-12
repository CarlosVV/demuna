package pe.gob.dmn.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.DemunaNivelDAO;
import pe.gob.dmn.model.DemunaNivel;
@Repository
public class DemunaNivelDAOImpl extends HibernateDaoSupport implements DemunaNivelDAO {

	@Autowired
	public DemunaNivelDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DemunaNivel> listaDemunaNivel() {
		return getHibernateTemplate().find(" from DemunaNivel where estado=1");
	}

	
}
