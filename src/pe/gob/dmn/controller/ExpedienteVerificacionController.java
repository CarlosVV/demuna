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
import pe.gob.dmn.model.ExpedientesVerificacion;
import pe.gob.dmn.service.ExpedienteVerificacionService;

@Controller
@Scope("session")
public class ExpedienteVerificacionController {

	private static final Logger logger = Logger
			.getLogger(ExpedienteController.class);

	@Autowired
	private ExpedienteVerificacionService expedienteVerificacionService;

	@RequestMapping(value = "/formverificacion/cargardatosverificacion.htm", method = RequestMethod.POST)
	public String cargarVerificacion(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug(" __cargarDatosVerificacion ");
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;
			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;
			logger.debug("EXPEDIENTE=" + request.getParameter("expediente"));
			List<ExpedientesVerificacion> lista = expedienteVerificacionService
					.obtenerPorExpediente(Integer.parseInt(request
							.getParameter("expediente")));

			logger.debug(" __size: " + lista.size());

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" data:[ ");
			Integer time = 0;
			for (int i = 0; i < lista.size(); i++) {
				if (i >= offset) {
					time++;
					logger.debug(" __time: " + time);
					response.getWriter().println("{ ");
					response.getWriter().println(
							" \"KyVerificacion\":\""
									+ lista.get(i).getKyverificacion()
									+ "\" ,  ");
					response.getWriter()
							.println(
									" \"KyExpediente\":\""
											+ (lista.get(i).getExpediente() != null ? lista
													.get(i).getExpediente()
													.getKyexpediente()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"HoraInicio\":\""
											+ (lista.get(i).getHorainicio() != null ? lista
													.get(i).getHorainicio()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"HoraFin\":\""
											+ (lista.get(i).getHorafin() != null ? lista
													.get(i).getHorafin() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"MotVerificacion\":\""
											+ (lista.get(i)
													.getMotverificacion() != null ? lista
													.get(i)
													.getMotverificacion() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Autorizacion\":\""
											+ (lista.get(i).getAutorizacion() != null ? lista
													.get(i).getAutorizacion()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"DocIdentidad\":\""
											+ (lista.get(i).getDocidentidad() != null ? lista
													.get(i).getDocidentidad()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"Defensor\":\""
											+ (lista.get(i).getDefensor() != null ? lista
													.get(i).getDefensor() : "")
											+ "\" ,  ");
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
									" \"Observacion\":\""
											+ (lista.get(i).getObservacion() != null ? lista
													.get(i).getObservacion()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"Concluciones\":\""
											+ (lista.get(i).getConcluciones() != null ? lista
													.get(i).getConcluciones()
													: "") + "\" ,  ");
					response.getWriter().println(
							" \"Fecha\":\""
									+ (lista.get(i).getFecha() != null ? lista
											.get(i).getFecha() : "") + "\" ");
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
			logger.debug(" __fin");
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

	@RequestMapping(value = "/formverificacion/nuevaverificacion.htm", method = RequestMethod.POST)
	public String nuevaVerificacion(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer kyverificacion = request.getParameter("Ky") != null ? Integer
					.parseInt(request.getParameter("Ky")) : 0;
			String Autorizacion = request.getParameter("Autorizacion") != null ? request
					.getParameter("Autorizacion") : "";
			String Concluciones = request.getParameter("Concluciones") != null ? request
					.getParameter("Concluciones") : "";
			String Nombres = request.getParameter("Nombres") != null ? request
					.getParameter("Nombres") : "";
			String Appaterno = request.getParameter("Appaterno") != null ? request
					.getParameter("Appaterno") : "";
			String Apmaterno = request.getParameter("Apmaterno") != null ? request
					.getParameter("Apmaterno") : "";
			Integer DocIdentidad = request.getParameter("DocIdentidad") != null ? Integer
					.parseInt(request.getParameter("DocIdentidad")) : 0;
			String FechaVerificacion = request
					.getParameter("FechaVerificacion") != null ? request
					.getParameter("FechaVerificacion") : "";
			String HoraFin = request.getParameter("HoraFin") != null ? request
					.getParameter("HoraFin") : "";
			String HoraInicio = request.getParameter("HoraInicio") != null ? request
					.getParameter("HoraInicio") : "";
			logger.debug(" __nuevaverificacion: "
					+ request.getParameter("KyExpediente"));
			Long kyexpediente = Long.parseLong(request
					.getParameter("KyExpediente"));
			String MotVerificacion = request.getParameter("MotVerificacion") != null ? request
					.getParameter("MotVerificacion") : "";
			String Observacion = request.getParameter("Observacion") != null ? request
					.getParameter("Observacion") : "";

			ExpedientesVerificacion ev = new ExpedientesVerificacion();
			if (kyverificacion > 0)
				ev.setKyverificacion(kyverificacion);
			ev.setAutorizacion(Autorizacion);
			ev.setConcluciones(Concluciones);
			ev.setNombres(Nombres);
			ev.setAppaterno(Appaterno);
			ev.setApmaterno(Apmaterno);
			ev.setDefensor(Nombres + " " + Appaterno + " " + Apmaterno);
			if (DocIdentidad > 0)
				ev.setDocidentidad(String.format("%08d", DocIdentidad));
			else
				ev.setDocidentidad("");
			ev.setFecha(FechaVerificacion);
			ev.setHorafin(HoraFin);
			ev.setHorainicio(HoraInicio);
			ev.setExpediente(new Expediente());
			ev.getExpediente().setKyexpediente(kyexpediente);
			ev.setMotverificacion(MotVerificacion);
			ev.setObservacion(Observacion);

			expedienteVerificacionService.registrarExpedienteVerificacion(ev);

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\" ");
			response.getWriter().println(
					" , \"msg\": \"Registro Actualizado correctamente.\"}");

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

	@RequestMapping(value = "/formverificacion/eliminarverificacion.htm", method = RequestMethod.POST)
	public String eliminarVerificacion(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			logger.debug(" __eliminarInformante ");
			Long kyVerificacion = Long.parseLong(request
					.getParameter("KyVerificacion"));

			expedienteVerificacionService.eliminarVerificacion(kyVerificacion);

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
