package pe.gob.dmn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.ExpedienteTransgresorDAO;
import pe.gob.dmn.model.ExpedientesTransgresor;

@Repository
public class ExpedienteTransgresorDAOImpl extends HibernateDaoSupport implements ExpedienteTransgresorDAO {

	@Autowired
	public ExpedienteTransgresorDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedientesTransgresor> obtenerPorExpediente(Integer kyexpediente) {		
       return getHibernateTemplate().find(" from ExpedientesTransgresor where kyexpediente="+kyexpediente);
	}
	@Override
	public void registrarExpedienteTransgresor(ExpedientesTransgresor et) {
		this.getHibernateTemplate().saveOrUpdate( et );
	}

	@Override
	public void actualizarExpedienteTransgresor(ExpedientesTransgresor et) {
		String valores = "";
		
		if (et.getEstcivil() != null)
			valores += (valores.length() > 0 ? ", " : "") + "dni = :dni";
		if (et.getDomicilio() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "domicilio = :domicilio";
		if (et.getEdad() != null)
			valores += (valores.length() > 0 ? ", " : "") + "edad = :edad";
		if (et.getEstado() != null)
			valores += (valores.length() > 0 ? ", " : "") + "estado = :estado";
		if (et.getEstcivil() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "estcivil = :estcivil";
		if (et.getExpediente() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "KYEXPEDIENTE = :KYEXPEDIENTE";
		if (et.getKydepartamento() != null)
			valores += (valores.length() > 0 ? ", " : "")
					+ "kydepartamento = :kydepartamento";		
		if (et.getNombre() != null) valores += (valores.length() > 0 ? ", " : "") + "nombre = :nombre";
		if (et.getNombres() != null) valores += (valores.length() > 0 ? ", " : "") + "nombres = :nombres";
		if (et.getApPaterno() != null) valores += (valores.length() > 0 ? ", " : "") + "appaterno = :appaterno";
		if (et.getApMaterno() != null) valores += (valores.length() > 0 ? ", " : "") + "apmaterno = :apmaterno";
		if (et.getOcupacion() != null)
			valores += (valores.length() > 0 ? ", " : "") + "ocupacion = :ocupacion";
		if (et.getRelacion() != null)
			valores += (valores.length() > 0 ? ", " : "") + "relacion = :relacion";
		if (et.getSexo() != null)
			valores += (valores.length() > 0 ? ", " : "") + "sexo = :sexo";		
		if (et.getTipo() != null)
			valores += (valores.length() > 0 ? ", " : "") + "tipo = :tipo";			

		Query query = getSession().createQuery(
				" update ExpedientesTransgresor set " + valores
						+ " where kytransgresor = :kytransgresor ");
				
		if (et.getDni() != null) query.setString("dni", et.getDni());
		if (et.getDomicilio() != null) query.setString("domicilio", et.getDomicilio());
		if (et.getEdad() != null) query.setInteger("edad", et.getEdad());
		if (et.getEstado() != null) query.setInteger("estado", et.getEstado());
		if (et.getEstcivil() != null) query.setString("estcivil", et.getEstcivil());
		if (et.getExpediente() != null) query.setLong("KYEXPEDIENTE", et.getExpediente().getKyexpediente());
		if (et.getKydepartamento() != null) query.setString("kydepartamento", et.getKydepartamento());		
		if (et.getNombre() != null) query.setString("nombre", et.getNombre());
		if (et.getNombres() != null) query.setString("nombres", et.getNombres());
		if (et.getApPaterno() != null) query.setString("appaterno", et.getApPaterno());
		if (et.getApMaterno() != null) query.setString("apmaterno", et.getApMaterno());
		if (et.getOcupacion() != null) query.setString("ocupacion", et.getOcupacion());
		if (et.getRelacion() != null) query.setString("relacion", et.getRelacion());
		if (et.getSexo() != null) query.setString("sexo", et.getSexo());
		if (et.getTipo() != null) query.setBigDecimal("tipo", et.getTipo());
				
		query.setLong("kytransgresor", et.getKytransgresor());		
		
		query.executeUpdate();
	}

	
	@Override
	public ExpedientesTransgresor obtenerPorTransgresor(Long kytransgresor) {		
		Query query = getSession().createQuery(" from ExpedientesTransgresor where kytransgresor= :kytransgresor ")
		.setLong("kytransgresor",kytransgresor );		
		return (ExpedientesTransgresor)query.uniqueResult();
	}
	
	public void eliminarTransgresor(Long kytransgresor) {
		logger.debug("eliminar Transgresor");
		Query query = getSession().createQuery(" delete from ExpedientesTransgresor where kytransgresor="+ kytransgresor);
       query.executeUpdate();
	}
}
