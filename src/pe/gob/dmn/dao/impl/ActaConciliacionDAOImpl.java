package pe.gob.dmn.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.ActaConciliacionDAO;
import pe.gob.dmn.model.ExpedientesConciliacione;

@Repository
public class ActaConciliacionDAOImpl extends HibernateDaoSupport implements ActaConciliacionDAO {

	@Autowired
	public ActaConciliacionDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedientesConciliacione> cargarDatosConciliacion(Long expediente) {
		
		Query query = getSession().createQuery("from ExpedientesConciliacione e" + 
		" where e.expediente.kyexpediente= :kyexpediente ")
        .setLong("kyexpediente", expediente);
       return (List<ExpedientesConciliacione>)query.list();
	}

	@Override
	public void registrarConciliacion(ExpedientesConciliacione conci){
		logger.debug("insert or update ExpedientesConciliacione ");
		this.getHibernateTemplate().saveOrUpdate( conci );
	}
	
	@Override
	public void eliminarConciliacion(Long kyacta){
		logger.debug("eliminar ExpedientesConciliacione");
		Query query = getSession().createQuery(" delete from ExpedientesConciliacione "+
		" where kyacta= :kyacta ")
        .setLong("kyacta", kyacta);
       query.executeUpdate();
	}
}
