package pe.gob.dmn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.gob.dmn.model.Discapacidad;
import pe.gob.dmn.service.DiscapacidadService;

@Controller
@Scope("session")
public class DiscapacidadController {

	private static final Logger logger = Logger
			.getLogger(DiscapacidadController.class);

	@Autowired
	private DiscapacidadService discapacidadService;

	@RequestMapping(value = "/formdiscapacidades/cargar.htm")
	public String cargar(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug(" cargar ");
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;
			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;

			List<Discapacidad> lista = discapacidadService
					.listarDiscapacidades();

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
							" \"KyDiscapacidad\":\""
									+ lista.get(i).getKydiscapacidad()
									+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Discapacidad\":\""
											+ lista.get(i).getDiscapacidad()
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Estado\":\"" + lista.get(i).getEstado()
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
								"{\"success\": false, \"errors\":{\"reason\": \"No se pudo agregar el registro.\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/formdiscapacidades/editardiscapacidad.htm")
	public String editardiscapacidad(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Integer kyDiscapacidad = Integer.parseInt(request
					.getParameter("txtKyDiscapacidad"));
			String discapacidad = request.getParameter("txtDiscapacidad");
			Integer estado = Integer.parseInt(request.getParameter("txtEstado")
					.substring(0, 1));

			Discapacidad dis = new Discapacidad();
			dis.setKydiscapacidad(kyDiscapacidad);
			dis.setDiscapacidad(discapacidad);
			dis.setEstado(estado);
			discapacidadService.registrarDiscapacidad(dis);

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\" ");
			response.getWriter().println(
					" , \"msg\": \"Registro Actualizado correctamente.\"}");

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

	@RequestMapping(value = "/formdiscapacidades/nuevadiscapacidad.htm")
	public String nuevadiscapacidad(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String discapacidad = "Nueva Discapacidad...";
			Integer estado = 0;

			Discapacidad dis = new Discapacidad();
			dis.setDiscapacidad(discapacidad);
			dis.setEstado(estado);
			discapacidadService.registrarDiscapacidad(dis);

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\" ");
			response.getWriter().println(
					" , \"msg\": \"Registro Actualizado correctamente.\"}");

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

	@RequestMapping(value = "/formdiscapacidades/eliminardiscapacidad.htm")
	public String eliminardiscapacidad(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Integer kyDiscapacidad = Integer.parseInt(request
					.getParameter("KyDiscapacidad"));
			discapacidadService.eliminarDiscapacidad(kyDiscapacidad);
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
