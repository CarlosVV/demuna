package pe.gob.dmn.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.ExpedienteSolicitudDAO;
import pe.gob.dmn.model.ExpedientesAfectado;
import pe.gob.dmn.model.ExpedientesSolicitud;
import pe.gob.dmn.model.Tipoatencion;

@Repository
public class ExpedienteSolicitudDAOImpl extends HibernateDaoSupport implements ExpedienteSolicitudDAO {

	@Autowired
	public ExpedienteSolicitudDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@Override
	public ExpedientesSolicitud obtenerPorExpedienteSolicitud(Integer kysolicitud) {
		Query query = getSession().createQuery(" from ExpedientesSolicitud where kysolicitud= :kysolicitud ")
        .setInteger("kysolicitud", kysolicitud);
       return (ExpedientesSolicitud)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedientesSolicitud> cargarSolicitud(Integer expediente) {
		
		Query query = getSession().createQuery("from ExpedientesSolicitud where kyexpediente= :kyexpediente ")
        .setInteger("kyexpediente", expediente);
       return (List<ExpedientesSolicitud>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedientesAfectado> cargarAfectado(Integer expediente) {
		
		Query query = getSession().createQuery("from ExpedientesAfectado e" + 
		" where e.expediente.kyexpediente= :kyexpediente ")
        .setInteger("kyexpediente", expediente);
       return (List<ExpedientesAfectado>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tipoatencion> cargarTipoAtencion() {
		
		Query query = getSession().createQuery("from Tipoatencion");
       
       return (List<Tipoatencion>)query.list();
	}
	
	@Override
	public void registrarSolicitud(ExpedientesSolicitud exp){
		logger.debug("insert or update expediente ");
		this.getHibernateTemplate().saveOrUpdate( exp );
	}
	
	@Override
	public void eliminarSolicitud(Integer kySolicitud){
		logger.debug("eliminar Solicitud");
		Query query = getSession().createQuery(" delete from ExpedientesSolicitud "+
		" where kySolicitud= :kySolicitud ")
        .setInteger("kySolicitud", kySolicitud);
       query.executeUpdate();
	}
}
