package pe.gob.dmn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.ExpedienteAfectadoDAO;
import pe.gob.dmn.model.ExpedientesAfectado;

@Repository
public class ExpedienteAfectadoDAOImpl extends HibernateDaoSupport implements ExpedienteAfectadoDAO {

	@Autowired
	public ExpedienteAfectadoDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedientesAfectado> obtenerPorExpediente(Integer kyexpediente) {		
       return getHibernateTemplate().find(" from ExpedientesAfectado where kyexpediente="+kyexpediente);
	}
	@Override
	public void registrarExpedienteAfectado(ExpedientesAfectado et) {
		this.getHibernateTemplate().saveOrUpdate( et );
	}

	@Override
	public void actualizarExpedienteAfectado(ExpedientesAfectado ea) {
		String valores = "";
		if (ea.getDiscapacidad() != null)
			valores += (valores.length() > 0 ? ", " : "") + "discapacidad = :discapacidad";
		if (ea.getEstcivil() != null)
			valores += (valores.length() > 0 ? ", " : "") + "dni = :dni";
		if (ea.getDomicilio() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "domicilio = :domicilio";
		if (ea.getEdad() != null)
			valores += (valores.length() > 0 ? ", " : "") + "edad = :edad";
		if (ea.getEstado() != null)
			valores += (valores.length() > 0 ? ", " : "") + "estado = :estado";
		if (ea.getEstcivil() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "estcivil = :estcivil";
		if (ea.getExpediente() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "KYEXPEDIENTE = :KYEXPEDIENTE";
		if (ea.getKydepartamento() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "kydepartamento = :kydepartamento";
		if (ea.getMadresoltera() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "madresoltera = :madresoltera";	
		if (ea.getNombre() != null) valores += (valores.length() > 0 ? ", " : "") + "nombre = :nombre";
		if (ea.getNombres() != null) valores += (valores.length() > 0 ? ", " : "") + "nombres = :nombres";
		if (ea.getApPaterno() != null) valores += (valores.length() > 0 ? ", " : "") + "appaterno = :appaterno";
		if (ea.getApMaterno() != null) valores += (valores.length() > 0 ? ", " : "") + "apmaterno = :apmaterno";
		if (ea.getSexo() != null)
			valores += (valores.length() > 0 ? ", " : "") + "sexo = :sexo";		
		if (ea.getTipo() != null)
			valores += (valores.length() > 0 ? ", " : "") + "tipo = :tipo";			

		Query query = getSession().createQuery(
				" update ExpedientesAfectado set " + valores
						+ " where kyAfectado= :kyAfectado ");
		
		if (ea.getDiscapacidad() != null) query.setString("discapacidad", ea.getDiscapacidad());
		if (ea.getDni() != null) query.setString("dni", ea.getDni());
		if (ea.getDomicilio() != null) query.setString("domicilio", ea.getDomicilio());
		if (ea.getEdad() != null) query.setInteger("edad", ea.getEdad());
		if (ea.getEstado() != null) query.setInteger("estado", ea.getEstado());
		if (ea.getEstcivil() != null) query.setString("estcivil", ea.getEstcivil());
		if (ea.getExpediente() != null) query.setLong("KYEXPEDIENTE", ea.getExpediente().getKyexpediente());
		if (ea.getKydepartamento() != null) query.setString("kydepartamento", ea.getKydepartamento());
		if (ea.getMadresoltera() != null) query.setString("madresoltera", ea.getMadresoltera());
		if (ea.getNombre() != null) query.setString("nombre", ea.getNombre());
		if (ea.getNombres() != null) query.setString("nombres", ea.getNombres());
		if (ea.getApPaterno() != null) query.setString("appaterno", ea.getApPaterno());
		if (ea.getApMaterno() != null) query.setString("apmaterno", ea.getApMaterno());
		if (ea.getSexo() != null) query.setString("sexo", ea.getSexo());
		if (ea.getTipo() != null) query.setBigDecimal("tipo", ea.getTipo());
				
		query.setLong("kyAfectado", ea.getKyafectado());		
		
		query.executeUpdate();
	}

	
	@Override
	public ExpedientesAfectado obtenerAfectado(Long kyAfectado) {		
		Query query = getSession().createQuery(" from ExpedientesAfectado where kyAfectado= :kyAfectado ")
		.setLong("kyAfectado",kyAfectado );		
		return (ExpedientesAfectado)query.uniqueResult();
	}
	
	public void eliminarAfectado(Long kyAfectado) {
		logger.debug("eliminar Afectado");
		Query query = getSession().createQuery(" delete from ExpedientesAfectado where kyAfectado="+ kyAfectado);
       query.executeUpdate();
	}
}
