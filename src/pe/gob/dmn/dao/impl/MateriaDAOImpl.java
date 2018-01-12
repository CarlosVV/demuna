package pe.gob.dmn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.MateriaDAO;
import pe.gob.dmn.model.Materia;
@Repository
public class MateriaDAOImpl extends HibernateDaoSupport implements MateriaDAO {


	@Autowired
	public MateriaDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Materia> listarMaterias() {
		return getHibernateTemplate().find(" from Materia order by kyMateria ");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Materia> listarMaterias(Integer estado) {
		return getHibernateTemplate().find(" from Materia where estmat = "+ estado + " order by kyMateria");
	}

	@Override
	public Materia obtenerMateria(Integer kyMateria) {
		logger.debug("obtener matria ");
		Query query = getSession().createQuery(" from Materia where kymateria= :kyMateria ")
        .setInteger("kyMateria", kyMateria);
       return (Materia)query.uniqueResult();
	}

	@Override
	public void registrarMateria(Materia mat) {
		logger.debug("update materia ");
		this.getHibernateTemplate().saveOrUpdate( mat );
	}

	@Override
	public void eliminarMateria(Integer kymateria) {
		logger.debug("eliminar Materia");
		Query query = getSession().createQuery(" delete from Materia where kymateria=:kymateria ")
        .setInteger("kymateria", kymateria);
       query.executeUpdate();
	}

}
