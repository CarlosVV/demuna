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
import pe.gob.dmn.model.ExpedientesCompromiso;
import pe.gob.dmn.service.ActaCompromisoService;

@Controller
@Scope("session")
public class ActaCompromisoController {

	private static final Logger logger = Logger
			.getLogger(ActaCompromisoController.class);

	@Autowired
	private ActaCompromisoService actaCompromisoService;
	
	@RequestMapping(value = "/formcompromiso/cargardatoscompromiso.htm")
	public String cargarDatosCompromiso(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");
			
			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;

			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;

			Long expediente = Long
					.parseLong(request.getParameter("expediente"));
			
			List<ExpedientesCompromiso> lista = 
					actaCompromisoService.cargarDatosCompromiso(expediente);
			
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
							" \"KyCompromiso\":\"" + lista.get(i).getKycompromiso()
									+ "\" ,  ");

					response.getWriter().println(
							" \"KyExpediente\":\"" + lista.get(i).getExpediente().getKyexpediente()
									+ "\" ,  ");
					response.getWriter().println(
							" \"DescripcionCaso\":\"" + lista.get(i).getDescripcioncaso()
									+ "\" ,  ");
					response.getWriter().println(
							" \"Compromiso\":\"" + lista.get(i).getCompromiso()
									+ "\" ,  ");
					response.getWriter().println(
							" \"Seguimiento\":\"" + lista.get(i).getSeguimiento()
									+ "\" ,  ");
					response.getWriter()
							.println(
						    " \"Fecha\":\"" + lista.get(i).getFecha()
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
	
	@RequestMapping(value = "/formcompromiso/eliminarcompromiso.htm")
	public String eliminarCompromiso(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Long KyCompromiso = Long.parseLong(request
					.getParameter("KyCompromiso"));
			actaCompromisoService.eliminarCompromiso(KyCompromiso);
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
	
	@RequestMapping(value = "/formcompromiso/nuevacompromiso.htm")
	public String nuevaCompromiso(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.debug("insert or update solicitud ");
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");
			
			ExpedientesCompromiso comp = new ExpedientesCompromiso(); 			
			
			Long kycompromiso = request.getParameter("Ky")!=null?Long.parseLong(request.getParameter("Ky")):0;														
			Long kyExpediente = request.getParameter("KyExpediente")!=null?Long.parseLong(request.getParameter("KyExpediente")):0;					
			String descripcioncaso = request.getParameter("DescripcionCaso");	
			String compromiso = request.getParameter("Compromiso");			
			String seguimiento =  request.getParameter("Seguimiento");	
			String fecha =  request.getParameter("FechaCompromiso");	
				
	
			
			if(kyExpediente!=0){		 
				Expediente expediente = new Expediente();				
				expediente.setKyexpediente(kyExpediente );
				comp.setExpediente(expediente);	 
			}
						
			if(kycompromiso!=0){				
				comp.setKycompromiso(kycompromiso);
			}			
			
			comp.setCompromiso(compromiso);			
			comp.setDescripcioncaso(descripcioncaso);			
			comp.setFecha(fecha);	 
			comp.setSeguimiento(seguimiento);
			
			actaCompromisoService.registrarCompromiso(comp);

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
}
