package pe.gob.dmn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.ExpedienteDAO;
import pe.gob.dmn.model.Expediente;

@Repository
public class ExpedienteDAOImpl extends HibernateDaoSupport implements
		ExpedienteDAO {

	@Autowired
	public ExpedienteDAOImpl(SessionFactory sessionFactory) {
		setHibernateTemplate(new HibernateTemplate(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Expediente> listarExpedientes(Integer KyDemuna) {
		return getHibernateTemplate().find(
				" from Expediente where demuna.kydemuna=" + KyDemuna
						+ " order by kyexpediente desc");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Expediente> listarExpedientes(String campo, String valor) {
		String where = campo != "" ? (" where " + campo + " like '%" + valor + "%'")
				: "";
		return getHibernateTemplate().find(
				" from Expediente " + where + " order by kyexpediente desc");
	}

	@Override
	public void crearExpediente(Expediente expediente) {
		this.getHibernateTemplate().saveOrUpdate(expediente);
	}

	@Override
	public void actualizarExpediente(Expediente expediente) {
		logger.debug("update Expediente ");

		Query query = getSession()
				.createQuery(
						" update Expediente set "
								+ "KyDependencia = :kydependencia, "
								+ "NroExpediente = :nroexpediente, "
								+ "Lugar = :lugar, "
								+ "Fecha = :fecha, "
								+ (expediente.getMateria() != null ? "KyMateria = :kymateria, "
										: "")
								+ "ResumenHechos = :resumenhechos, "
								+ "Acciones = :acciones, "
								+ "EstExp = :estado, "
								+ "Derivacion = :derivacion, "
								+ "DerivaDe = :derivade, "
								+ "C1 = :c1, "
								+ "C2 = :c2, " + "C3 = :c3, " + "C4 = :c4, "
								+ "C5 = :c5, " + "C6 = :c6 "
								+ "where kyExpediente= :kyexpediente ")
				.setString("kydependencia",
						expediente.getDemuna().getKydemuna().toString())
				.setString("nroexpediente", expediente.getNroexpediente())
				.setString("lugar", expediente.getLugar())
				.setString("fecha", expediente.getFecha());
		
		if (expediente.getMateria() != null) {
			query.setString("kymateria", expediente.getMateria().getKymateria()
					.toString());
		}

		query.setString("resumenhechos", expediente.getResumenhechos())
				.setString("acciones", expediente.getAcciones())
				.setString("estado", expediente.getEstexp().toString())
				.setString("derivacion", expediente.getDerivacion())
				.setString("derivade", expediente.getDerivaDe())
				.setString("c1", expediente.getC1())
				.setString("c2", expediente.getC2())
				.setString("c3", expediente.getC3())
				.setString("c4", expediente.getC4())
				.setString("c5", expediente.getC5())
				.setString("c6", expediente.getC6())
				.setString("kyexpediente",
						expediente.getKyexpediente().toString());
		query.executeUpdate();
	}
	
	@Override
	public void eliminarExpediente(Integer kyexpediente) {
		logger.debug("eliminar Expediente");
		Query query = getSession().createQuery(" delete from Expediente where kyexpediente=:kyexpediente ")
        .setInteger("kyexpediente", kyexpediente);
       query.executeUpdate();
	}

	@Override
	public Expediente obtenerExpediente(Integer kyExpediente) {
		Query query = getSession().createQuery(" from Expediente where kyexpediente=:kyexpediente ")
        .setInteger("kyexpediente", kyExpediente);
       return (Expediente)query.uniqueResult();
	}

}
