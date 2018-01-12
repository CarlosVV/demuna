package pe.gob.dmn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.DemunaDAO;
import pe.gob.dmn.model.Demuna;

@Repository
public class DemunaDAOImpl extends HibernateDaoSupport implements DemunaDAO {

	@Autowired
	public DemunaDAOImpl(SessionFactory sessionFactory) {
		setHibernateTemplate(new HibernateTemplate(sessionFactory));
	}

	@Override
	public Demuna obtener(Integer kyDemuna) {
		Query query = getSession().createQuery(
				" from Demuna where kydemuna= :kyDemuna ").setInteger(
				"kyDemuna", kyDemuna);
		
		return (Demuna) query.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Demuna> listarDemunas() {
		return getHibernateTemplate().find(" from Demuna ");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Demuna> listarDemunas(Integer kyTipoDemuna) {
		return getHibernateTemplate().find(
				" from Demuna where KYTIPODEMUNA=" + kyTipoDemuna);
	}

	@Override
	public void registrarDemuna(Demuna dem) {
		this.getHibernateTemplate().saveOrUpdate(dem);
	}

	@Override
	public void actualizarImagen(Demuna dem) {
		logger.debug(dem.getKydemuna() + " actualizarImagen " + dem.getImagen());
		Query query = getSession()
				.createQuery(
						" update Demuna set imagen = :imagen, archivo= :archivo where kyDemuna = :kyDemuna ")
				.setString("imagen", dem.getImagen())
				.setBinary("archivo", dem.getArchivo())
				.setInteger("kyDemuna", dem.getKydemuna());

		query.executeUpdate();
	}

	@Override
	public void eliminarDemuna(Integer kyDemuna) {
		logger.debug("eliminar demunas");
		Query query = getSession().createQuery(
				" delete from Demuna where kydemuna=:kydemuna").setInteger(
				"kydemuna", kyDemuna);
		query.executeUpdate();
	}

}
