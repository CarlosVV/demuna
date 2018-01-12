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
import pe.gob.dmn.model.ExpedientesConciliacione;
import pe.gob.dmn.service.ActaConciliacionService;

@Controller
@Scope("session")
public class ActaConciliacionController {

	private static final Logger logger = Logger
			.getLogger(ActaConciliacionController.class);

	@Autowired
	private ActaConciliacionService actaConciliacionService;

	
	@RequestMapping(value = "/formconciliaciones/cargardatosconciliacion.htm")
	public String cargarDatosConciliacion(HttpServletRequest request,
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
			
			List<ExpedientesConciliacione> lista = 
					actaConciliacionService.cargarDatosConciliacion(expediente);
			
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
							" \"KyActa\":\"" + lista.get(i).getKyacta()
									+ "\" ,  ");

					response.getWriter().println(
							" \"KyExpediente\":\"" + lista.get(i).getExpediente().getKyexpediente()
									+ "\" ,  ");
					response.getWriter().println(
							" \"Acuerdos\":\"" + lista.get(i).getAcuerdos()
									+ "\" ,  ");
					response.getWriter().println(
							" \"Seguimiento\":\"" + lista.get(i).getSeguimiento()
									+ "\" ,  ");
					response.getWriter().println(
							" \"Fecha\":\"" + lista.get(i).getFecha()
									+ "\" ,  ");
					response.getWriter().println(
							" \"p1\":\"" + lista.get(i).getP1()
									+ "\" ,  ");
					response.getWriter().println(
							" \"p2\":\"" + lista.get(i).getP2()
									+ "\" ,  ");
					response.getWriter().println(
							" \"p3\":\"" + lista.get(i).getP3()
									+ "\" ,  ");
					response.getWriter().println(
							" \"p4\":\"" + lista.get(i).getP4()
									+ "\" ,  ");
					response.getWriter().println(
							" \"p5\":\"" + lista.get(i).getP5()
									+ "\" ,  ");
					response.getWriter()
							.println(
							" \"conciliacion\":\"" + lista.get(i).getConciliacion()
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
		
	
	@RequestMapping(value = "/formconciliaciones/eliminarconciliacion.htm")
	public String eliminarConciliacion(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Long kyActa = Long.parseLong(request
					.getParameter("KyActa"));
			actaConciliacionService.eliminarConciliacion(kyActa);
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
	
	@RequestMapping(value = "/formconciliaciones/nuevaconciliacion.htm")
	public String nuevaConciliacion(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.debug("insert or update solicitud ");
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");
			
			ExpedientesConciliacione conc = new ExpedientesConciliacione();  
			
			Long kyacta = request.getParameter("Ky")!=null?Long.parseLong(request.getParameter("Ky")):0;
			Long kyExpediente = request.getParameter("KyExpediente")!=null?Long.parseLong(request.getParameter("KyExpediente")):0;		
			String acuerdos = request.getParameter("Informe");		
			String p1 = request.getParameter("ck1");
			String p2 = request.getParameter("ck2");
			String p3 = request.getParameter("ck3");
			String p4 = request.getParameter("ck4");
			String p5 = request.getParameter("ck5");			
			String seguimiento =  request.getParameter("Otros");
			String fecha =  request.getParameter("FechaConciliacion");
			
			conc.setAcuerdos(acuerdos);
			conc.setP1(p1);
			conc.setP2(p2);
			conc.setP3(p3);
			conc.setP4(p4);
			conc.setP5(p5);
			conc.setSeguimiento(seguimiento);
			conc.setFecha(fecha);
			
			if(kyExpediente!=0){		 
				Expediente expediente = new Expediente();				
				expediente.setKyexpediente(kyExpediente );
				conc.setExpediente(expediente);	 
			}			
			
			if(kyacta!=0){			
				conc.setKyacta(kyacta);
			}
			
			actaConciliacionService.registrarConciliacion(conc);

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
