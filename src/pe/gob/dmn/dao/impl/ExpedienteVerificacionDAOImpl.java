package pe.gob.dmn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.ExpedienteVerificacionDAO;
import pe.gob.dmn.model.ExpedientesVerificacion;

@Repository
public class ExpedienteVerificacionDAOImpl extends HibernateDaoSupport
		implements ExpedienteVerificacionDAO {

	@Autowired
	public ExpedienteVerificacionDAOImpl(SessionFactory sessionFactory) {
		setHibernateTemplate(new HibernateTemplate(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedientesVerificacion> obtenerPorExpediente(
			Integer kyexpediente) {
		return getHibernateTemplate().find(
				" from ExpedientesVerificacion where kyexpediente="
						+ kyexpediente);
	}

	@Override
	public void registrarExpedienteVerificacion(ExpedientesVerificacion ev) {
		this.getHibernateTemplate().saveOrUpdate(ev);
	}

	@Override
	public ExpedientesVerificacion obtenerVerificacion(Long kyVerificacion) {
		Query query = getSession()
				.createQuery(
						" from ExpedientesVerificacion where kyverificacion= :kyVerificacion ")
				.setLong("kyVerificacion", kyVerificacion);
		return (ExpedientesVerificacion) query.uniqueResult();
	}

	public void eliminarVerificacion(Long kyVerificacion) {
		logger.debug("eliminar Verificacion");
		Query query = getSession().createQuery(
				" delete from ExpedientesVerificacion where kyverificacion = "
						+ kyVerificacion);
		query.executeUpdate();
	}
}
