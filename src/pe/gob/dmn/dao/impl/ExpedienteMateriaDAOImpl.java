package pe.gob.dmn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.ExpedienteMateriaDAO;
import pe.gob.dmn.model.ExpedientesMateria;

@Repository
public class ExpedienteMateriaDAOImpl extends HibernateDaoSupport implements ExpedienteMateriaDAO {

	@Autowired
	public ExpedienteMateriaDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedientesMateria> obtenerPorExpediente(Integer kyexpediente) {		
       return getHibernateTemplate().find(" from ExpedientesMateria where kyexpediente="+kyexpediente);
	}
	@Override
	public void registrarExpedienteMateria(ExpedientesMateria em) {
		this.getHibernateTemplate().saveOrUpdate( em );
	}	
	
	@Override
	public ExpedientesMateria obtenerMateria(Long kyMateria) {		
		Query query = getSession().createQuery(" from ExpedientesMateria where kyMateria= :kyMateria ")
		.setLong("kyMateria",kyMateria );		
		return (ExpedientesMateria)query.uniqueResult();
	}
	
	public void eliminarMateria(Long kyMateria) {
		logger.debug("eliminar Materia");
		Query query = getSession().createQuery(" delete from ExpedientesMateria where kyexpedientemateria = "+ kyMateria);
       query.executeUpdate();
	}
}
