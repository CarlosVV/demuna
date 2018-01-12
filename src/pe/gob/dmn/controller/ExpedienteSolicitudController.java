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

import pe.gob.dmn.model.Expediente;
import pe.gob.dmn.model.ExpedientesAfectado;
import pe.gob.dmn.model.ExpedientesSolicitud;
import pe.gob.dmn.model.Tipoatencion;
import pe.gob.dmn.service.ExpedienteSolicitudService;

@Controller
@Scope("session")
public class ExpedienteSolicitudController {

	private static final Logger logger = Logger
			.getLogger(ExpedienteSolicitudController.class);

	@Autowired
	private ExpedienteSolicitudService expSolicitudService;

	@RequestMapping(value = "/formsolicitudes/cargartipoapoyo.htm")
	public String cargartipoapoyo(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;

			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;

			List<Tipoatencion> lista = expSolicitudService.cargarTipoAtencion();

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
							" \"KyTipoAtencion\":\""
									+ lista.get(i).getKytipoatencion()
									+ "\" ,  ");

					response.getWriter().println(
							" \"Atencion\":\""
									+ lista.get(i).getKytipoatencion() + "."
									+ lista.get(i).getAtencion() + "\" ");
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
								"{\"success\": false, \"errors\":{\"reason\": \"Error en cargar tipo de atencion.\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/formsolicitudes/cargarsolicitud.htm", method = RequestMethod.POST)
	public String cargarSolicitud(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;

			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;

			Integer expediente = Integer.parseInt(request
					.getParameter("expediente"));

			List<ExpedientesSolicitud> lista = expSolicitudService
					.cargarSolicitud(expediente);

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
							" \"KySolicitud\":\""
									+ lista.get(i).getKysolicitud() + "\" ,  ");

					response.getWriter().println(
							" \"KyAfectado\":\""
									+ lista.get(i).getExpedientesAfectado()
											.getKyafectado() + "\" ,  ");
					response.getWriter().println(
							" \"KyTipoAtencion\":\""
									+ lista.get(i).getTipoatencion()
											.getKytipoatencion() + "\" ,  ");
					response.getWriter()
							.println(
									" \"Nombres\":\""
											+ (lista.get(i).getNombres() != null ? lista
													.get(i).getNombres() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Appaterno\":\""
											+ (lista.get(i).getAppaterno() != null ? lista
													.get(i).getAppaterno() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Apmaterno\":\""
											+ (lista.get(i).getApmaterno() != null ? lista
													.get(i).getApmaterno() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Senor\":\""
											+ (lista.get(i).getSenor() != null ? lista
													.get(i).getSenor() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Cargo\":\""
											+ (lista.get(i).getCargo() != null ? lista
													.get(i).getCargo() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Institucion\":\""
											+ (lista.get(i).getInstitucion() != null ? lista
													.get(i).getInstitucion()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"Informe\":\""
											+ (lista.get(i).getInforme() != null ? lista
													.get(i).getInforme() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Otros\":\""
											+ (lista.get(i).getOtros() != null ? lista
													.get(i).getOtros() : "")
											+ "\" ,  ");
					response.getWriter().println(
							" \"Fecha\":\"" + lista.get(i).getFecha()
									+ "\" ,  ");
					response.getWriter().println(
							" \"Derivaa\":\"" + lista.get(i).getDerivaa()
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
								"{\"success\": false, \"errors\":{\"reason\": \"Error en cargar solicitud.\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/formsolicitudes/cargarafectado.htm", method = RequestMethod.POST)
	public String cargarafectado(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;

			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;

			Integer expediente = Integer.parseInt(request
					.getParameter("expediente"));

			List<ExpedientesAfectado> lista = expSolicitudService
					.cargarAfectado(expediente);

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
							" \"KyAfectado\":\"" + lista.get(i).getKyafectado()
									+ "\" ,  ");

					response.getWriter()
							.println(
									" \"Nombre\":\"" + lista.get(i).getNombre()
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
								"{\"success\": false, \"errors\":{\"reason\": \"Error en obtener afectados.\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/formsolicitudes/nuevasolicitud.htm")
	public String nuevasolicitud(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.debug("insert or update solicitud ");
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			ExpedientesSolicitud exp = new ExpedientesSolicitud();

			Long ky = request.getParameter("Ky") != null ? Long
					.parseLong(request.getParameter("Ky")) : 0;
			Long KyExpediente = request.getParameter("KyExpediente") != null ? Long
					.parseLong(request.getParameter("KyExpediente")) : 0;
			Long KyAfectado = (request.getParameter("KyAfectado") != null && request
					.getParameter("KyAfectado").trim() != "") ? Long
					.parseLong(request.getParameter("KyAfectado")) : 0;
			Long KyTipoAtencion = request.getParameter("KyTipoAtencion") != null ? Long
					.parseLong(request.getParameter("KyTipoAtencion")) : 0;
			String nombres = request.getParameter("Nombres");
			String appaterno = request.getParameter("Appaterno");
			String apmaterno = request.getParameter("Apmaterno");
			String fecha = request.getParameter("FechaSolicitud") != null ? request
					.getParameter("FechaSolicitud") : "";
			String cargo = request.getParameter("Cargo");
			String institucion = request.getParameter("Institucion");
			String informe = request.getParameter("Informe");
			String otros = request.getParameter("Otros");
			String derivaa = request.getParameter("Derivar");

			logger.debug("fecha solicitud " + fecha);

			if (KyExpediente > 0) {
				exp.setExpediente(new Expediente());
				exp.getExpediente().setKyexpediente(KyExpediente);
			}

			exp.setExpedientesAfectado(new ExpedientesAfectado());
			exp.getExpedientesAfectado().setKyafectado(KyAfectado);

			exp.setTipoatencion(new Tipoatencion());
			exp.getTipoatencion().setKytipoatencion(KyTipoAtencion);

			exp.setNombres(nombres);
			exp.setApmaterno(apmaterno);
			exp.setAppaterno(appaterno);
			exp.setSenor(nombres + " " + appaterno + " " + apmaterno);
			exp.setCargo(cargo);
			exp.setInstitucion(institucion);
			exp.setInforme(informe);
			exp.setOtros(otros);
			exp.setDerivaa(derivaa);
			exp.setFecha(fecha);

			if (ky > 0) {
				exp.setKysolicitud(ky);
			}

			expSolicitudService.registrarSolicitud(exp);

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\" ");
			response.getWriter().println(
					" , \"msg\": \"Registro Actualizado correctamente.\"}");
		} catch (Exception e) {
			try {
				response.getWriter()
						.println(
								"{\"success\": false, \"errors\":{\"reason\": \"Error al grabar.\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/formsolicitudes/eliminarsolicitud.htm", method = RequestMethod.POST)
	public String eliminarSolicitud(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Integer KySolicitud = Integer.parseInt(request
					.getParameter("KySolicitud"));
			expSolicitudService.eliminarSolicitud(KySolicitud);
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
