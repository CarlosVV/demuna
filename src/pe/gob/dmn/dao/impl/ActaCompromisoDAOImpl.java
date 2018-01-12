package pe.gob.dmn.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.ActaCompromisoDAO;
import pe.gob.dmn.model.ExpedientesCompromiso;

@Repository
public class ActaCompromisoDAOImpl extends HibernateDaoSupport implements ActaCompromisoDAO {

	@Autowired
	public ActaCompromisoDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedientesCompromiso> cargarDatosCompromiso(Long expediente) {
		
		Query query = getSession().createQuery("from ExpedientesCompromiso e" + 
		" where e.expediente.kyexpediente= :kyexpediente ")
        .setLong("kyexpediente", expediente);
       return (List<ExpedientesCompromiso>)query.list();
	}

	@Override
	public void registrarCompromiso(ExpedientesCompromiso comp){
		logger.debug("insert or update ExpedientesCompromiso ");
		this.getHibernateTemplate().saveOrUpdate( comp );
	}
	
	@Override
	public void eliminarCompromiso(Long kycompromiso){
		logger.debug("eliminar ExpedientesCompromiso");
		Query query = getSession().createQuery(" delete from ExpedientesCompromiso "+
		" where kycompromiso = :kycompromiso ")
        .setLong("kycompromiso", kycompromiso);
       query.executeUpdate();
	}
}
