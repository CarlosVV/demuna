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

import pe.gob.dmn.model.DemunaNivel;
import pe.gob.dmn.service.DemunaNivelService;

import pe.gob.dmn.model.Demuna;
import pe.gob.dmn.service.DemunaService;

@Controller
@Scope("session")
public class DemunaNivelController {

	private static final Logger logger = Logger
			.getLogger(DemunaNivelController.class);

	@Autowired
	private DemunaNivelService demunaNivelService;

	@Autowired
	private DemunaService demunaService;

	@RequestMapping(value = "/niveldemunas/cargarDemunasNacionales.htm", method = RequestMethod.POST)
	public String cargarDemunasNacionales(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			logger.debug(" tipo: " + request.getParameter("tipo"));
			Integer tipo = Integer.parseInt(request.getParameter("tipo"));
			List<Demuna> lista = demunaService.listarDemunas(tipo);

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\" ,  ");
			logger.debug(" lista " + lista.size());
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" data:[ ");
			for (int i = 0; i < lista.size(); i++) {
				response.getWriter().println("{ ");
				response.getWriter().println(
						" \"KyDemuna\":\"" + lista.get(i).getKydemuna()
								+ "\" ,  ");
				response.getWriter().println(
						" \"Nombre\":\"" + lista.get(i).getKydemuna() + " . "
								+ lista.get(i).getNombre() + "\" ");
				response.getWriter().println("}");
				if ((i + 1) < lista.size()) {
					response.getWriter().println(",");
				}
			}
			response.getWriter().println(" ] ");
			response.getWriter().println(" }");
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

	@RequestMapping(value = "/niveldemunas/cargarTipoDemuna.htm")
	public String cargarTipoDemuna(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			List<DemunaNivel> lista = demunaNivelService.listaDemunaNivel();

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\" ,  ");
			logger.debug(" lista " + lista.size());
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" data:[ ");
			for (int i = 0; i < lista.size(); i++) {
				response.getWriter().println("{ ");
				response.getWriter().println(
						" \"KyTipoDemuna\":\"" + lista.get(i).getKytipodemuna()
								+ "\" ,  ");
				response.getWriter()
						.println(
								" \"Descripcion\":\""
										+ lista.get(i).getKytipodemuna()
										+ " . " + lista.get(i).getDescripcion()
										+ "\" ");
				response.getWriter().println("}");
				if ((i + 1) < lista.size()) {
					response.getWriter().println(",");
				}
			}
			response.getWriter().println(" ] ");
			response.getWriter().println(" }");
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
}
