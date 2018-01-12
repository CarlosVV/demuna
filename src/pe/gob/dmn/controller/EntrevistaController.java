package pe.gob.dmn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.gob.dmn.model.Expediente;
import pe.gob.dmn.model.ExpedientesEntrevista;
import pe.gob.dmn.service.EntrevistaService;



@Controller
@Scope("session")
public class EntrevistaController {

	private static final Logger logger = Logger
			.getLogger(EntrevistaController.class);

	@Autowired
	private EntrevistaService entrevistaService;
	
	@RequestMapping(value = "/formentrevista/cargarEntrevista.htm")
	public String cargarSolicitud(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");
			
			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;

			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;

			Integer expediente = Integer
					.parseInt(request.getParameter("expediente"));
			
			List<ExpedientesEntrevista> lista = 
					entrevistaService.cargarEntrevista(expediente);
			
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
							" \"KyEntrevista\":\"" + lista.get(i).getKyentrevista()
									+ "\" ,  ");
					
					response.getWriter().println(
							" \"KyExpediente\":\"" + lista.get(i).getExpediente().getKyexpediente()
									+ "\" ,  ");
					
					response.getWriter().println(
							" \"Tipo\":\"" + lista.get(i).getTipo()
									+ "\" ,  ");
					
					response.getWriter().println(
							" \"Nombre\":\"" + lista.get(i).getNombre()
									+ "\" ,  ");
					
					response.getWriter().println(
							" \"Nombres\":\"" + (lista.get(i).getNombres()!=null?lista.get(i).getNombres():"")
									+ "\" ,  ");
					
					response.getWriter().println(
							" \"Appaterno\":\"" + (lista.get(i).getAppaterno()!=null?lista.get(i).getAppaterno():"")
									+ "\" ,  ");
					response.getWriter().println(
							" \"Apmaterno\":\"" + (lista.get(i).getApmaterno()!=null?lista.get(i).getApmaterno():"")
									+ "\" ,  ");
					
					
					response.getWriter().println(
							" \"Informe\":\"" + (lista.get(i).getInforme()!=null?lista.get(i).getInforme():"")
									+ "\" ,  ");
					
					response.getWriter().println(
							" \"Otros\":\"" + (lista.get(i).getOtros()!=null?lista.get(i).getOtros():"")
									+ "\" ,  ");
					
					response.getWriter()
							.println(
									" \"Fecha\":\"" + (lista.get(i).getFecha()!=null?lista.get(i).getFecha():"")
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
		
	@RequestMapping(value = "/formentrevistas/nuevaEntrevista.htm")
	public String nuevasolicitud(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.debug("insert or update solicitud ");
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");		
			
			
			
			Long ky = request.getParameter("Ky")!=null?Long.parseLong(request.getParameter("Ky")):0;																
			Long  kyExpediente = request.getParameter("KyExpediente")!=null?Long.parseLong(request.getParameter("KyExpediente")):0;		
			String tipo =request.getParameter("Tipo");
			String nombres = request.getParameter("Nombres");
			String appaterno = request.getParameter("Appaterno");
			String apmaterno = request.getParameter("Apmaterno");
			String informe = request.getParameter("Informe");
			String otros = request.getParameter("Otros");			
			String fecha = request.getParameter("FechaEntrevista");	
			
			ExpedientesEntrevista ent = new ExpedientesEntrevista();
			ent.setTipo(tipo);
			ent.setNombres(nombres);
			ent.setAppaterno(appaterno);
			ent.setApmaterno(apmaterno);
			ent.setNombre(nombres + " " +  appaterno + " " + apmaterno);
			ent.setInforme(informe);
			ent.setOtros(otros);
			ent.setFecha(fecha);
						
			Expediente exp = new Expediente();
			
			if (kyExpediente!=0){
				exp.setKyexpediente(kyExpediente);
				ent.setExpediente(exp );
			}
			
			if(ky!=0){
				ent.setKyentrevista(ky);
			}
			
			entrevistaService.registrarEntrevista(ent);

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
	
	@RequestMapping(value = "/formentrevistas/eliminarEntrevista.htm")
	public String eliminarEntrevista(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Integer kyEntrevista = Integer.parseInt(request
					.getParameter("KyEntrevista"));
			entrevistaService.eliminarEntrevista(kyEntrevista);
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
