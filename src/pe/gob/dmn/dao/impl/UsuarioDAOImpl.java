package pe.gob.dmn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.UsuarioDAO;
import pe.gob.dmn.model.Usuario;

@Repository
public class UsuarioDAOImpl extends HibernateDaoSupport implements UsuarioDAO {

	@Autowired
	public UsuarioDAOImpl(SessionFactory sessionFactory) {
		setHibernateTemplate(new HibernateTemplate(sessionFactory));
	}

	@Override
	public Usuario autenticar(String usuario, String clave) {
		Query query = getSession()
				.createQuery(
						" from Usuario where upper(usuario)= upper(:usr) and clave= :clave and estado=1 ")
				.setString("usr", usuario).setString("clave", clave);
		return (Usuario) query.uniqueResult();
	}

	@Override
	public void actualizar(String usuario, String clave) {
		logger.debug("actualizando al usuario");
		Query query = getSession()
				.createQuery(
						" update Usuario set clave = :clave where upper(usuario)= upper(:usr) ")
				.setString("usr", usuario).setString("clave", clave);
		query.executeUpdate();
	}

	@Override
	public void actualizarUsuarioDetalle(String kyUsuario, String kyDemunaIni,
			String nombres, String appaterno, String apmaterno,
			String telefonos, String correo) {
		logger.debug(" __actualizando al usuario ");

		Query query = getSession()
				.createQuery(
						" update Usuario set nombre = :nombre, KYDEMUNA = :KYDEMUNA, "
								+ "nombres = :nombres, appaterno = :appaterno, apmaterno = :apmaterno, "
								+ "telefonos = :telefonos, email = :email "
								+ "where kyusuario= :kyusuario ")
				.setString("nombre",
						nombres + " " + appaterno + " " + apmaterno)
				.setString("KYDEMUNA", kyDemunaIni)
				.setString("nombres", nombres)
				.setString("appaterno", appaterno)
				.setString("apmaterno", apmaterno).setString("email", correo)
				.setString("telefonos", telefonos)
				.setString("kyusuario", kyUsuario);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarUsuarios() {
		return getHibernateTemplate().find(
				" from Usuario order by kyusuario desc ");
	}

	@Override
	public void registrarUsuario(Usuario usu) {
		logger.debug("insert or update usuario ");
		this.getHibernateTemplate().saveOrUpdate(usu);
	}

	public void eliminarUsuario(Integer kyusuario) {
		logger.debug("eliminar usuario");
		Query query = getSession().createQuery(
				" delete from Usuario where kyusuario=:kyusuario ").setInteger(
				"kyusuario", kyusuario);
		query.executeUpdate();
	}
}