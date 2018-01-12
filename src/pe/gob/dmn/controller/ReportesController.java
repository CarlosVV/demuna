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
import pe.gob.dmn.model.Materia;
import pe.gob.dmn.model.ReporteDemunasMeses;
import pe.gob.dmn.model.ReporteDemunasMesesMaterias;
import pe.gob.dmn.model.ReporteDemunasTodo;
import pe.gob.dmn.model.Usuario;
import pe.gob.dmn.service.ExpedienteAfectadoService;
import pe.gob.dmn.service.ExpedienteInformanteService;
import pe.gob.dmn.service.ExpedienteMateriaService;
import pe.gob.dmn.service.ExpedienteService;
import pe.gob.dmn.service.ExpedienteTransgresorService;
import pe.gob.dmn.service.MateriaService;
import pe.gob.dmn.service.ReportesService;

@Controller
@Scope("session")
public class ReportesController {

	private static final Logger logger = Logger
			.getLogger(ReportesController.class);

	@Autowired
	private ExpedienteService expedienteService;
	
	@Autowired
	private MateriaService materiaService;

	@Autowired
	private ExpedienteMateriaService expedienteMateriaService;

	@Autowired
	private ExpedienteInformanteService expedienteInformanteService;
	
	@Autowired
	private ExpedienteAfectadoService expedienteAfectadoService;
	
	@Autowired
	private ExpedienteTransgresorService expedienteTransgresorService;

	@Autowired
	private ReportesService reportesService;		

	@RequestMapping(value = "/reportes/aarepordemunasmeses.htm")
	public String aarepordemunasmeses(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug(" /reportes/aarepordemunasmeses.htm ");
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;

			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;

			Integer kydemuna = ((Usuario) request.getSession().getAttribute(
					"usrLogin")).getDemuna().getKydemuna();

			List<ReporteDemunasMeses> lista = reportesService
					.getReporteDemunasMeses(kydemuna);

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" data:[ ");

			Integer time = 0;
			for (int i = 0; i < lista.size(); i++) {
				if (i >= offset) {
					response.getWriter().println("{ ");

					response.getWriter().println(
							" \"Casos\":\"" + lista.get(i).getCasos()
									+ "\" ,  ");

					response.getWriter().println(
							" \"Demuna\":\"" + lista.get(i).getDemuna()
									+ "\" ,  ");

					response.getWriter().println(
							" \"Meses\":\"" + lista.get(i).getMeses() + "\" ");

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
								"{\"success\": false, \"errors\":{\"reason\": \"No se pudo cargar reporte.\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}

		return null;

	}

	@RequestMapping(value = "/reportes/agrafdemuna.htm")
	public String agrafdemuna(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug(" /reportes/agrafdemuna.htm ");
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;

			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;

			Integer kydemuna = ((Usuario) request.getSession().getAttribute(
					"usrLogin")).getDemuna().getKydemuna();
			
			String iniDateString = request.getParameter("ini");
			String finDateString = request.getParameter("fin");
			
			logger.debug(" Fecha1 " + iniDateString );
			logger.debug(" Fecha2 " + finDateString );

			List<ReporteDemunasMesesMaterias> lista;

			if (iniDateString!=null && iniDateString.trim() != "" && 
					finDateString!=null && finDateString.trim() != "") {
				lista = reportesService.getReporteDemunasGraficoFecha(kydemuna,
						iniDateString, finDateString);
			} else {
				lista = reportesService.getReporteDemunasGrafico(kydemuna);
				
			}

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" data:[ ");

			Integer time = 0;
			for (int i = 0; i < lista.size(); i++) {
				if (i >= offset) {
					response.getWriter().println("{ ");

					response.getWriter().println(
							" \"Casos\":\"" + lista.get(i).getCasos()
									+ "\" ,  ");

					response.getWriter().println(
							" \"Nombres\":\"" + lista.get(i).getMateria()
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
								"{\"success\": false, \"errors\":{\"reason\": \"No se pudo cargar reporte.\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}

		return null;

	}

	@RequestMapping(value = "/reportes/aarepordemunasmesesmaterias.htm")
	public String aarepordemunasmesesmaterias(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug(" /reportes/aarepordemunasmesesmaterias.htm ");
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;

			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;

			Integer kydemuna = ((Usuario) request.getSession().getAttribute(
					"usrLogin")).getDemuna().getKydemuna();

			List<ReporteDemunasMesesMaterias> lista = reportesService
					.getReporteDemunasMesesMaterias(kydemuna);

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" data:[ ");

			Integer time = 0;
			for (int i = 0; i < lista.size(); i++) {
				if (i >= offset) {
					response.getWriter().println("{ ");

					response.getWriter().println(
							" \"Casos\":\"" + lista.get(i).getCasos()
									+ "\" ,  ");

					response.getWriter().println(
							" \"Materia\":\"" + lista.get(i).getMateria()
									+ "\" ,  ");

					response.getWriter().println(
							" \"Meses\":\"" + lista.get(i).getMeses() + "\" ");

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
								"{\"success\": false, \"errors\":{\"reason\": \"No se pudo cargar reporte.\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}

		return null;

	}
	
	@RequestMapping(value = "/reportes/aarepordemunasall.htm")
	public String aarepordemunasall(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug(" /reportes/aarepordemunasall.htm ");
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;

			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;			

			String iniDateString = request.getParameter("ini");
			String finDateString = request.getParameter("fin");
			
			List<ReporteDemunasTodo> lista;

			if (iniDateString!=null && iniDateString.trim() != "" && 
					finDateString!=null && finDateString.trim() != "") {
				lista = reportesService.getReporteDemunasTodoPorFecha(iniDateString, finDateString);				
			} else {
				lista = reportesService.getReporteDemunasTodo();
			}

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" data:[ ");

			Integer time = 0;
			for (int i = 0; i < lista.size(); i++) {
				if (i >= offset) {
					response.getWriter().println("{ ");

					response.getWriter().println(
							" \"Casos\":\"" + lista.get(i).getCasos()
									+ "\" ,  ");

					response.getWriter()
							.println(
									" \"Demuna\":\"" + lista.get(i).getDemuna()
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
								"{\"success\": false, \"errors\":{\"reason\": \"No se pudo cargar reporte.\"}}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}

		return null;
	}
	

	@RequestMapping(value = "/expedientePrint.htm", method = RequestMethod.GET)
	public String aaaexpedienteprint(HttpServletRequest request,
			HttpServletResponse response) {
		Integer expediente = Integer.parseInt(request.getParameter("xfile"));
		logger.debug("kyExpediente" + expediente);

		Expediente ex = expedienteService.obtenerExpediente(expediente);
		request.setAttribute("expediente", ex);
		request.setAttribute("expedienteMaterias", expedienteMateriaService.obtenerPorExpediente(expediente));
		request.setAttribute("expedienteInformantes", expedienteInformanteService.obtenerPorExpediente(expediente));
		request.setAttribute("expedienteAfectados", expedienteAfectadoService.obtenerPorExpediente(expediente));
		request.setAttribute("expedienteTransgresor", expedienteTransgresorService.obtenerPorExpediente(expediente));
		// expediente => id para llamarlo desde el jsp

		return "expedientePrint";
	}

	public List<Materia> listarMaterias(){
		return materiaService.listarMaterias();
	}
	
	@RequestMapping(value = "/reporteAnualExcel.htm")
	public String reporteAnualExcel(HttpServletRequest request,
			HttpServletResponse response) {
				
		logger.debug(" __Reporte Excel");
		
		Integer kydemuna = ((Usuario) request.getSession().getAttribute(
				"usrLogin")).getDemuna().getKydemuna();	
		
		String dwnldd = request.getParameter("dwnldd");		
		
		if (dwnldd!=null){		
			response.setContentType("application/vnd.ms-excel");
			response.setIntHeader("Expires", 0);
			response.setHeader("Cache-Control"," must-revalidate, post-check=0, pre-check=0");
			response.setHeader("content-disposition"," attachment;filename=exportformat.xls");
		}
		
		request.setAttribute("materias", materiaService.listarMaterias());			
		request.setAttribute("reporte01", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"01"));
		request.setAttribute("reporte02", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"02"));
		request.setAttribute("reporte03", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"03"));
		request.setAttribute("reporte04", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"04"));
		request.setAttribute("reporte05", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"05"));
		request.setAttribute("reporte06", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"06"));
		request.setAttribute("reporte07", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"07"));
		request.setAttribute("reporte08", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"08"));
		request.setAttribute("reporte09", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"09"));
		request.setAttribute("reporte10", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"10"));
		request.setAttribute("reporte11", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"11"));
		request.setAttribute("reporte12", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"12"));
		
		return "reporteAnualExcel";
	}
	
	@RequestMapping(value = "/reporteAnualExcel.htm",method=RequestMethod.GET)
	public String reporteAnualExcelGET(HttpServletRequest request,
			HttpServletResponse response) {
				
		logger.debug(" __Reporte Excel");
		
		Integer kydemuna = ((Usuario) request.getSession().getAttribute(
				"usrLogin")).getDemuna().getKydemuna();	
		
		String dwnldd = request.getParameter("dwnldd");		
		
		if (dwnldd!=null){		
			response.setContentType("application/vnd.ms-excel");
			response.setIntHeader("Expires", 0);
			response.setHeader("Cache-Control"," must-revalidate, post-check=0, pre-check=0");
			response.setHeader("content-disposition"," attachment;filename=exportformat.xls");
		}
		
		request.setAttribute("materias", materiaService.listarMaterias());			
		request.setAttribute("reporte01", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"01"));
		request.setAttribute("reporte02", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"02"));
		request.setAttribute("reporte03", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"03"));
		request.setAttribute("reporte04", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"04"));
		request.setAttribute("reporte05", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"05"));
		request.setAttribute("reporte06", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"06"));
		request.setAttribute("reporte07", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"07"));
		request.setAttribute("reporte08", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"08"));
		request.setAttribute("reporte09", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"09"));
		request.setAttribute("reporte10", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"10"));
		request.setAttribute("reporte11", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"11"));
		request.setAttribute("reporte12", reportesService.getReporteDemunasMesesMateriasPorMes(kydemuna,"12"));
		
		return "reporteAnualExcel";
	}

}
