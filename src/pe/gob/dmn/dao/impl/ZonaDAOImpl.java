package pe.gob.dmn.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import pe.gob.dmn.dao.ZonaDAO;
import pe.gob.dmn.model.Zona;

@Repository
public class ZonaDAOImpl extends HibernateDaoSupport implements ZonaDAO {

	@Autowired
	public ZonaDAOImpl(SessionFactory sessionFactory) {
		setHibernateTemplate(new HibernateTemplate(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Zona> listarZonas(String Ubigeo) {
		return getHibernateTemplate().find(
				" from Zona where ubigeo=" + Ubigeo + " order by codzona ");
	}

}
