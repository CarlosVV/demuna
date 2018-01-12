package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.UsuarioDAO;
import pe.gob.dmn.model.Usuario;
import pe.gob.dmn.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	public Usuario autenticar(String usuario, String clave) {
		return usuarioDAO.autenticar(usuario, clave);
	}
	
	@Override
	public void actualizar(String usuario, String clave) {
		usuarioDAO.actualizar(usuario, clave);
	}
	
	@Override
	public void actualizarUsuarioDetalle(String kyUsuario, String kyDemunaIni,String nombres,
			String appaterno,String apmaterno, String telefonos,String correo){
		usuarioDAO.actualizarUsuarioDetalle(kyUsuario,kyDemunaIni,nombres,appaterno,apmaterno,telefonos,correo);
	}

	@Override
	public List<Usuario> listarUsuarios(){
		return usuarioDAO.listarUsuarios();	
	}
	
	@Override
	public void registrarUsuario(Usuario usu){
		 usuarioDAO.registrarUsuario(usu);
	}
	
	@Override
	public void eliminarUsuario(Integer kyusuario){
		usuarioDAO.eliminarUsuario(kyusuario);
	}
}
