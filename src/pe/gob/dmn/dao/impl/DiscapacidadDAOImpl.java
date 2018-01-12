package pe.gob.dmn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.DiscapacidadDAO;
import pe.gob.dmn.model.Discapacidad;
@Repository
public class DiscapacidadDAOImpl extends HibernateDaoSupport implements DiscapacidadDAO {


	@Autowired
	public DiscapacidadDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Discapacidad> listarDiscapacidades() {
		return getHibernateTemplate().find(" from Discapacidad ");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Discapacidad> listarDiscapacidades(Integer Estado) {
		return getHibernateTemplate().find(" from Discapacidad where estado="+ Estado);
	}
	
	@Override
	public void registrarDiscapacidad(Discapacidad dis) {
		logger.debug("update Discap ");
		this.getHibernateTemplate().saveOrUpdate( dis );
	}

	@Override
	public void eliminarDiscapacidad(Integer kyDiscapacidad) {
		logger.debug("eliminar Discap");
		Query query = getSession().createQuery(" delete from Discapacidad where kydiscapacidad=:kydiscapacidad ")
        .setInteger("kydiscapacidad", kyDiscapacidad);
       query.executeUpdate();
	}

}
