package pe.gob.dmn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.ExpedienteInformanteDAO;
import pe.gob.dmn.model.ExpedientesInformante;

@Repository
public class ExpedienteInformanteDAOImpl extends HibernateDaoSupport implements
		ExpedienteInformanteDAO {

	@Autowired
	public ExpedienteInformanteDAOImpl(SessionFactory sessionFactory) {
		setHibernateTemplate(new HibernateTemplate(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedientesInformante> obtenerPorExpediente(Integer kyexpediente) {
		return getHibernateTemplate().find(
				" from ExpedientesInformante where kyexpediente="
						+ kyexpediente);
	}

	@Override
	public void registrarExpedienteInformante(ExpedientesInformante ei) {
		this.getHibernateTemplate().saveOrUpdate(ei);
	}

	@Override
	public void actualizarExpedienteInformante(ExpedientesInformante ei) {
		String valores = "";
		if (ei.getEstcivil() != null)
			valores += (valores.length() > 0 ? ", " : "") + "dni = :dni";
		if (ei.getDomicilio() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "domicilio = :domicilio";
		if (ei.getEdad() != null)
			valores += (valores.length() > 0 ? ", " : "") + "edad = :edad";
		if (ei.getEstcivil() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "estcivil = :estcivil";
		if (ei.getExpediente() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "KYEXPEDIENTE = :KYEXPEDIENTE";
		if (ei.getKydepartamento() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "kydepartamento = :kydepartamento";
		if (ei.getNombre() != null)
			valores += (valores.length() > 0 ? ", " : "") + "nombre = :nombre";
		if (ei.getNombres() != null)
			valores += (valores.length() > 0 ? ", " : "") + "nombres = :nombres";
		if (ei.getApPaterno() != null)
			valores += (valores.length() > 0 ? ", " : "") + "appaterno = :appaterno";
		if (ei.getApMaterno() != null)
			valores += (valores.length() > 0 ? ", " : "") + "apmaterno = :apmaterno";
		if (ei.getOcupacion() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "ocupacion = :ocupacion";
		if (ei.getRelacionAfectado() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "relacionAfectado = :relacion";
		if (ei.getSexo() != null)
			valores += (valores.length() > 0 ? ", " : "") + "sexo = :sexo";
		if (ei.getTelefono() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "telefono = :telefono";
		if (ei.getTipo() != null)
			valores += (valores.length() > 0 ? ", " : "") + "tipo = :tipo";
		if (ei.getEstado() != null)
			valores += (valores.length() > 0 ? ", " : "") + "estado = :estado";

		Query query = getSession().createQuery(
				" update ExpedientesInformante set " + valores
						+ " where kyinformante= :kyinformante ");		

		if (ei.getDni() != null) query.setString("dni", ei.getDni());
		if (ei.getDomicilio() != null) query.setString("domicilio", ei.getDomicilio());
		if (ei.getEdad() != null) query.setInteger("edad", ei.getEdad());		
		if (ei.getEstcivil() != null) query.setString("estcivil", ei.getEstcivil());
		if (ei.getExpediente() != null) query.setLong("KYEXPEDIENTE", ei.getExpediente().getKyexpediente());
		if (ei.getKydepartamento() != null) query.setString("kydepartamento", ei.getKydepartamento());
		if (ei.getNombre() != null) query.setString("nombre", ei.getNombre());
		if (ei.getNombres() != null) query.setString("nombres", ei.getNombres());
		if (ei.getApPaterno() != null) query.setString("appaterno", ei.getApPaterno());
		if (ei.getApMaterno() != null) query.setString("apmaterno", ei.getApMaterno());
		if (ei.getOcupacion() != null) query.setString("ocupacion", ei.getOcupacion());
		if (ei.getRelacionAfectado() != null) query.setString("relacion", ei.getRelacionAfectado());
		if (ei.getSexo() != null) query.setString("sexo", ei.getSexo());
		if (ei.getTelefono() != null) query.setString("telefono", ei.getTelefono());
		if (ei.getTipo() != null) query.setBigDecimal("tipo", ei.getTipo());
		if (ei.getEstado() != null) query.setInteger("estado", ei.getEstado());		

		query.setLong("kyinformante", ei.getKyinformante());		
		
		query.executeUpdate();
	}

	@Override
	public ExpedientesInformante obtenerInformante(Long kyInformante) {
		Query query = getSession()
				.createQuery(
						" from ExpedientesInformante where kyInformante= :kyInformante ")
				.setLong("kyInformante", kyInformante);
		return (ExpedientesInformante) query.uniqueResult();
	}

	public void eliminarInformante(Long kyInformante) {
		logger.debug("eliminar Informante");
		Query query = getSession().createQuery(
				" delete from ExpedientesInformante where kyInformante="
						+ kyInformante);
		query.executeUpdate();
	}
}
