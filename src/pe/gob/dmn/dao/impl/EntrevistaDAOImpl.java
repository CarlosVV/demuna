package pe.gob.dmn.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.EntrevistaDAO;
import pe.gob.dmn.model.ExpedientesEntrevista;

@Repository
public class EntrevistaDAOImpl extends HibernateDaoSupport implements EntrevistaDAO {

	@Autowired
	public EntrevistaDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedientesEntrevista> cargarEntrevista(Integer expediente) {
		
		Query query = getSession().createQuery("from ExpedientesEntrevista e" + 
			" where e.expediente.kyexpediente= :kyexpediente ")
        .setInteger("kyexpediente", expediente);
       return (List<ExpedientesEntrevista>)query.list();
	}
	
	@Override
	public void registrarEntrevista(ExpedientesEntrevista ent){
		logger.debug("insert or update ExpedientesEntrevista ");
		this.getHibernateTemplate().saveOrUpdate( ent );
	}
	
	@Override
	public void eliminarEntrevista(Integer kyEntrevista){
		logger.debug("eliminarEntrevista");
		Query query = getSession().createQuery(" delete from ExpedientesEntrevista "+
		" where kyEntrevista= :kyEntrevista ")
        .setInteger("kyEntrevista", kyEntrevista);
       query.executeUpdate();
	}
}
