package pe.gob.dmn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.gob.dmn.model.Demuna;
import pe.gob.dmn.model.Usuario;
import pe.gob.dmn.service.DemunaService;
import pe.gob.dmn.service.UsuarioService;

@Controller
@Scope("session")
public class LoginController {

	private static final Logger logger = Logger
			.getLogger(LoginController.class);

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private DemunaService demunaService;

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String preLogin() {
		logger.debug("PANTALLA DE LOGIN");
		return "login";
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			logger.debug("autenticando usr=" + request.getParameter("usr")
					+ "; clave=" + request.getParameter("clave"));

			Usuario usr = usuarioService.autenticar(
					request.getParameter("usr"), request.getParameter("clave"));
			
			if (usr != null) {
				request.getSession().setAttribute("usrLogin", usr);
				logger.debug(" usuario encontrado " + usr.toString());
				logger.debug(" kydemuna " + usr.getDemuna().getKydemuna());
				Demuna dem = demunaService.obtener(usr.getDemuna().getKydemuna());				
				request.getSession().setAttribute("demunaLogin", dem);
				response.getWriter().println("{success:true}");
			} else {
				logger.debug("usuario is null");
				response.getWriter()
						.println(
								"{\"success\": false, \"errors\":{\"reason\": \"Usuario o Clave Incorrecta.\"}}");
			}

		} catch (Exception e) {
			try {
				response.getWriter().println(
						"{\"success\": false, \"errors\":{\"reason\": \""
								+ e.getMessage() + "\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/loginupdate.htm", method = RequestMethod.POST)
	public String actualizarUsuario(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			String usu = "";
			// si quieres el usuario que esta en sesion:
			usu = ((Usuario) request.getSession().getAttribute("usrLogin"))
					.getUsuario();
			String clave = request.getParameter("clave");
			String claveNueva = request.getParameter("txtClaveNueva");
			String claveNuevaConfirm = request.getParameter("txtClaveConfirm");

			if (clave.equals(claveNueva)) {
				logger.error("clave nueva y antigua iguales");
				response.getWriter()
						.println(
								"{\"success\": false, \"errors\":{\"reason\": \"La clave nueva debe ser difeente a la clave acutal\"}}");
				return null;
			}

			if (!claveNuevaConfirm.equals(claveNueva)) {
				logger.error("claves no coindicen");
				response.getWriter()
						.println(
								"{\"success\": false, \"errors\":{\"reason\": \"La clave nueva no coindice\"}}");
				return null;
			}

			logger.debug("actualizando usuario = " + usu + " clave=" + clave
					+ " nueva= " + claveNueva);
			usuarioService.actualizar(usu,
					request.getParameter("txtClaveNueva"));

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

	@RequestMapping("/index.htm")
	public String index() {

		return "index";
	}

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		logger.debug("LOGOFF");
		request.getSession().setAttribute("usrLogin", null);
		request.getSession(false);
		return "login";
	}
}
