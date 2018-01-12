package pe.gob.dmn.service;

import pe.gob.dmn.model.Usuario;
import java.util.List;

public interface UsuarioService {

	public Usuario autenticar(String usuario, String clave);
	
	public void actualizar(String usuario, String clave);
	
	public void actualizarUsuarioDetalle(String kyUsuario, String kyDemunaIni,String nombres,
			String appaterno,String apmaterno,String telefonos,String correo);
	
	public List<Usuario> listarUsuarios();

	public void registrarUsuario(Usuario usu);

	public void eliminarUsuario(Integer kymateria);

}
