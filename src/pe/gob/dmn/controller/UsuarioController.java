package pe.gob.dmn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.gob.dmn.model.Usuario;
import pe.gob.dmn.model.Demuna;
import pe.gob.dmn.service.UsuarioService;
import pe.gob.dmn.service.DemunaService;

@Controller
@Scope("session")
public class UsuarioController {

	private static final Logger logger = Logger
			.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DemunaService demunaService;

	@RequestMapping(value = "/frmusuariosupdate/editarusuario.htm", method = RequestMethod.POST)
	public String actualizarUsuarioDetalle(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			String usu = "";
			usu = ((Usuario) request.getSession().getAttribute("usrLogin"))
					.getUsuario();
			String txtKyUsuario = request.getParameter("txtKyUsuario");
			String txtKyDemunaIni = request.getParameter("txtKyDemunaIni");
			String txtNombres = request.getParameter("txtNombres");
			String txtAppaterno = request.getParameter("txtAppaterno");
			String txtApmaterno = request.getParameter("txtApmaterno");
			String txtTelefonos = request.getParameter("txtTelefonos");
			String txtCorreo = request.getParameter("txtCorreo");

			logger.debug("actualizando usuario = " + usu);
			usuarioService.actualizarUsuarioDetalle(txtKyUsuario,
					txtKyDemunaIni, txtNombres, txtAppaterno, txtApmaterno,
					txtTelefonos, txtCorreo);

			response.getWriter().println("{success:true}");
		} catch (Exception e) {
			try {
				response.getWriter()
						.println(
								"{\"success\": false, \"errors\":{\"reason\": \"Error en actualizacion de usuario.\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/frmusuariosupdate/cargar.htm")
	public String cargarUpdateUsuarios(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@RequestMapping(value = "/formusuarios/cargar.htm")
	public String cargarUsuarios(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug(" cargar usuarios ");
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;

			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;

			List<Usuario> lista = usuarioService.listarUsuarios();

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" data:[ ");

			Integer time = 0;
			for (int i = 0; i < lista.size(); i++) {
				if (i >= offset) {
					time++;
					response.getWriter().println("{ ");

					response.getWriter().println(
							" \"kyusuario\":\"" + lista.get(i).getKyusuario()
									+ "\" ,  ");

					response.getWriter().println(
							" \"usuario\":\"" + lista.get(i).getUsuario()
									+ "\" ,  ");

					response.getWriter().println(
							" \"clave\":\"" + lista.get(i).getClave()
									+ "\" ,  ");

					response.getWriter().println(
							" \"nombre\":\""
									+ (lista.get(i).getNombre() != null ? lista
											.get(i).getNombre() : "") + "\", ");

					response.getWriter()
							.println(
									" \"nombres\":\""
											+ (lista.get(i).getNombres() != null ? lista
													.get(i).getNombres() : "")
											+ "\", ");

					response.getWriter()
							.println(
									" \"appaterno\":\""
											+ (lista.get(i).getAppaterno() != null ? lista
													.get(i).getAppaterno() : "")
											+ "\", ");

					response.getWriter()
							.println(
									" \"apmaterno\":\""
											+ (lista.get(i).getApmaterno() != null ? lista
													.get(i).getApmaterno() : "")
											+ "\", ");

					response.getWriter().println(
							" \"email\":\""
									+ (lista.get(i).getEmail() != null ? lista
											.get(i).getEmail() : "") + "\",  ");

					response.getWriter()
							.println(
									" \"telefonos\":\""
											+ (lista.get(i).getTelefonos() != null ? lista
													.get(i).getTelefonos() : "")
											+ "\", ");

					response.getWriter().println(
							" \"KyDemuna\":\""
									+ (lista.get(i).getDemuna() != null ? lista
											.get(i).getDemuna().getKydemuna()
											: "") + "\", ");

					response.getWriter().println(
							" \"nivel\":\"" + lista.get(i).getNivel() + "\", ");

					response.getWriter()
							.println(
									" \"estado\":\"" + lista.get(i).getEstado()
											+ "\" ");
					response.getWriter().println("}");

					if (time == size) {
						break;
					} else {
						if ((i + 1) < lista.size()) {
							response.getWriter().println(",");
						}
					}
				}
			}
			response.getWriter().println(" ] ");
			response.getWriter().println(" }");

		} catch (Exception e) {
			try {
				response.getWriter()
						.println(
								"{\"success\": false, \"errors\":{\"reason\": \"No se pudo cargar usuarios.\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/formusuarios/cargartipodemuna.htm")
	public String cargarTipodemuna(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug(" cargar usuarios ");
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;

			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 500;

			List<Demuna> lista = demunaService.listarDemunas();

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" dataTipoDemuna:[ ");

			Integer time = 0;
			for (int i = 0; i < lista.size(); i++) {
				if (i >= offset) {
					time++;
					response.getWriter().println("{ ");

					response.getWriter().println(
							" \"KyDemuna\":\"" + lista.get(i).getKydemuna()
									+ "\" ,  ");
					response.getWriter().println(
							" \"Nombre\":\"" + lista.get(i).getKydemuna()
									+ " . " + lista.get(i).getNombre()
									+ "\" ,  ");
					response.getWriter().println("}");

					if (time == size) {
						break;
					} else {
						if ((i + 1) < lista.size()) {
							response.getWriter().println(",");
						}
					}
				}
			}
			response.getWriter().println(" ] ");
			response.getWriter().println(" }");

		} catch (Exception e) {
			try {
				response.getWriter()
						.println(
								"{\"success\": false, \"errors\":{\"reason\": \"No se pudo cargar usuarios.\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/formusuarios/nuevousuario.htm")
	public String nuevoUsuario(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			logger.debug("Creando usuario");

			String usuario = "000-0000-00000";
			String nombres = "Nuevo Usuario...";
			String nombre = "Nuevo Usuario...";
			String appaterno = "Nuevo Usuario...";
			String apmaterno = "Nuevo Usuario...";
			Integer estado = 0;

			Usuario usu = new Usuario();
			usu.setUsuario(usuario);
			usu.setNombre(nombre);
			usu.setNombres(nombres);
			usu.setAppaterno(appaterno);
			usu.setApmaterno(apmaterno);
			usu.setEstado(estado);
			usu.setClave(" ");
			usu.setEmail("");
			usu.setNivel(0);
			usu.setTelefonos(" ");

			usuarioService.registrarUsuario(usu);

			response.getWriter().println("{success:true}");
		} catch (Exception e) {
			try {
				response.getWriter()
						.println(
								"{\"success\": false, \"errors\":{\"reason\": \"No se pudo agregar registro.\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/formusuarios/editarusuario.htm", method = RequestMethod.POST)
	public String editarusuario(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Integer kyusuario = Integer.parseInt(request
					.getParameter("txtKyUsuario"));
			String usuario = request.getParameter("txtUsuario");
			String nombres = request.getParameter("txtUsuNombres");
			String appaterno = request.getParameter("txtUsuAppaterno");
			String apmaterno = request.getParameter("txtUsuApmaterno");
			String email = request.getParameter("txtEmail");
			Integer nivel = Integer.parseInt(request.getParameter("txtNivel")
					.substring(0, 1));
			String clave = request.getParameter("txtClave");
			String telefonos = request.getParameter("txtTelefonos");
			Integer estado = Integer.parseInt(request.getParameter("txtEstado")
					.substring(0, 1));
			Integer kydemuna = Integer.parseInt(request
					.getParameter("comboTipoDemunaGuardar"));

			Usuario usu = new Usuario();
			Demuna dem = new Demuna();
			dem.setKydemuna(kydemuna);
			usu.setKyusuario(kyusuario);
			usu.setUsuario(usuario);
			usu.setNombres(nombres);
			usu.setAppaterno(appaterno);
			usu.setApmaterno(apmaterno);
			usu.setNombre(nombres + " " + appaterno + " " + apmaterno);
			usu.setEmail(email);
			usu.setNivel(nivel);
			usu.setClave(clave);
			usu.setTelefonos(telefonos);
			usu.setEstado(estado);
			usu.setDemuna(dem);

			usuarioService.registrarUsuario(usu);

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\" ");
			response.getWriter().println(
					" , \"msg\": \"Registro Actualizado correctamente.\"}");

		} catch (Exception e) {
			try {
				response.getWriter().println(
						"{\"success\": false, \"errors\":{\"reason\": \""
								+ e.getMessage().replace('\"', ' ') + "\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/formusuarios/eliminausuario.htm")
	public String eliminausuario(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Integer kyusuario = Integer.parseInt(request
					.getParameter("kyusuario"));
			usuarioService.eliminarUsuario(kyusuario);
			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\" ");
			response.getWriter().println(" , \"msg\": \"Eliminado \"}");

		} catch (Exception e) {
			try {
				response.getWriter()
						.println(
								"{\"success\": false, \"errors\":{\"reason\": \"No se pudo Eliminar el registro. "
										+ e.getMessage() + "\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}
}
