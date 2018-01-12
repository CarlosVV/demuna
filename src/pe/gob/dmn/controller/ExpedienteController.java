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

import pe.gob.dmn.model.Demuna;
import pe.gob.dmn.model.Discapacidad;
import pe.gob.dmn.model.Expediente;
import pe.gob.dmn.model.ExpedientesAfectado;
import pe.gob.dmn.model.ExpedientesInformante;
import pe.gob.dmn.model.ExpedientesMateria;
import pe.gob.dmn.model.ExpedientesTransgresor;
import pe.gob.dmn.model.Location;
import pe.gob.dmn.model.Materia;
import pe.gob.dmn.model.Zona;
import pe.gob.dmn.service.DiscapacidadService;
import pe.gob.dmn.service.ExpedienteAfectadoService;
import pe.gob.dmn.service.ExpedienteInformanteService;
import pe.gob.dmn.service.ExpedienteMateriaService;
import pe.gob.dmn.service.ExpedienteService;
import pe.gob.dmn.service.ExpedienteTransgresorService;
import pe.gob.dmn.service.LocationService;
import pe.gob.dmn.service.MateriaService;
import pe.gob.dmn.service.ZonaService;

@Controller
@Scope("session")
public class ExpedienteController {

	private static final Logger logger = Logger
			.getLogger(ExpedienteController.class);

	@Autowired
	private ZonaService zonaService;

	@Autowired
	private LocationService locationsService;

	@Autowired
	private MateriaService materiaService;

	@Autowired
	private ExpedienteService expedienteService;

	@Autowired
	private DiscapacidadService discapacidadService;

	@Autowired
	private ExpedienteTransgresorService expedienteTransgresorService;

	@Autowired
	private ExpedienteInformanteService expedienteInformanteService;

	@Autowired
	private ExpedienteAfectadoService expedienteAfectadoService;

	@Autowired
	private ExpedienteMateriaService expedienteMateriaService;

	@RequestMapping(value = "/formexpedientes/cargarDepartamentos.htm")
	public String cargarDepartamentos(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug(" cargarDepartamentos ");
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");
			List<Location> lista = locationsService.listarDepartamentos();

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" data:[ ");
			for (int i = 0; i < lista.size(); i++) {
				response.getWriter().println("{ ");
				response.getWriter().println(
						" \"KyDepartamento\":\"" + lista.get(i).getCoddpto()
								+ "\" ,  ");
				response.getWriter().println(
						" \"Departamento\":\"" + lista.get(i).getCoddpto()
								+ " " + lista.get(i).getName() + "\" ");
				response.getWriter().println("}");
				if ((i + 1) < lista.size()) {
					response.getWriter().println(",");
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

	@RequestMapping(value = "/formexpedientes/cargarProvincias.htm", method = RequestMethod.POST)
	public String cargarProvincias(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");
			logger.debug(" __cargarProvincias dpto: "
					+ request.getParameter("dpto"));
			String Departamento = request.getParameter("dpto");
			List<Location> lista = locationsService
					.listarProvincias(Departamento);

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" data:[ ");
			for (int i = 0; i < lista.size(); i++) {
				response.getWriter().println("{ ");
				response.getWriter().println(
						" \"KyProvincia\":\"" + lista.get(i).getCodprov()
								+ "\" ,  ");
				response.getWriter().println(
						" \"Provincia\":\"" + lista.get(i).getCodprov() + " "
								+ lista.get(i).getName() + "\" ");
				response.getWriter().println("}");
				if ((i + 1) < lista.size()) {
					response.getWriter().println(",");
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

	@RequestMapping(value = "/formexpedientes/cargarDistritos.htm", method = RequestMethod.POST)
	public String cargarDistritos(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");
			String Departamento = request.getParameter("dpto");
			String Provincia = request.getParameter("prov");
			logger.debug(" cargarDistritos dpto: " + Departamento + " / prov: "
					+ Provincia);

			List<Location> lista = locationsService.listarDistritos(
					Departamento, Provincia);

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" data:[ ");
			for (int i = 0; i < lista.size(); i++) {
				response.getWriter().println("{ ");
				response.getWriter().println(
						" \"KyDistrito\":\"" + lista.get(i).getCoddist()
								+ "\" ,  ");
				response.getWriter().println(
						" \"Distrito\":\"" + lista.get(i).getCoddist() + " "
								+ lista.get(i).getName() + "\" ");
				response.getWriter().println("}");
				if ((i + 1) < lista.size()) {
					response.getWriter().println(",");
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

	@RequestMapping(value = "/formexpedientes/cargarZonas.htm", method = RequestMethod.POST)
	public String cargarZonas(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");
			String Departamento = request.getParameter("dpto");
			String Provincia = request.getParameter("prov");
			String Distrito = request.getParameter("dist");

			String Ubigeo = Departamento + Provincia + Distrito;

			logger.debug(" cargarZonas / Ubigeo: " + Ubigeo);

			List<Zona> lista = zonaService.listarZonas(Ubigeo);

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" data:[ ");

			response.getWriter().println("{ ");
			response.getWriter().println(" \"codzona\":\"00\" ,  ");
			response.getWriter().println(" \"zonas\":\"00 SIN DEFINIR\" ");
			response.getWriter().println("}");

			for (int i = 0; i < lista.size(); i++) {
				if (i == 0)
					response.getWriter().println(",");
				response.getWriter().println("{ ");
				response.getWriter().println(
						" \"codzona\":\"" + lista.get(i).getCodzona()
								+ "\" ,  ");
				response.getWriter().println(
						" \"zonas\":\"" + lista.get(i).getCodzona() + " "
								+ lista.get(i).getZona() + "\" ");
				response.getWriter().println("}");
				if ((i + 1) < lista.size()) {
					response.getWriter().println(",");
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

	@RequestMapping(value = "/formexpedientes/cargar.htm")
	public String cargarGET(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug(" __cargar ");

		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;
			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;

			String campo = request.getParameter("campo") != null ? request
					.getParameter("campo") : "";
			String valor = request.getParameter("valor") != null ? request
					.getParameter("valor") : "";

			List<Expediente> lista;
			if (request.getParameter("demuna") == null) {
				lista = expedienteService.listarExpedientes(campo, valor);
			} else {
				lista = expedienteService.listarExpedientes(Integer
						.parseInt(request.getParameter("demuna")));
			}

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" dataEspec:[ ");

			Integer time = 0;
			for (int i = 0; i < lista.size(); i++) {
				if (i >= offset) {
					time++;
					response.getWriter().println("{ ");
					response.getWriter()
							.println(
									" \"KyExpediente\":\""
											+ lista.get(i).getKyexpediente()
											+ "\" ,  ");
					response.getWriter().println(
							" \"KyDependencia\":\""
									+ (lista.get(i).getDemuna() != null ? lista
											.get(i).getDemuna().getKydemuna()
											: "") + "\" ,  ");
					response.getWriter().println(
							" \"NroExpediente\":\""
									+ lista.get(i).getNroexpediente()
									+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Fecha\":\""
											+ (lista.get(i).getFecha() != null ? lista
													.get(i).getFecha() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Materia\":\""
											+ (lista.get(i).getMateria() != null ? lista
													.get(i).getMateria()
													.getKymateria()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"Lugar\":\""
											+ (lista.get(i).getLugar() != null ? lista
													.get(i).getLugar() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"ResumenHechos\":\""
											+ (lista.get(i).getResumenhechos() != null ? lista
													.get(i).getResumenhechos()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"Acciones\":\""
											+ (lista.get(i).getAcciones() != null ? lista
													.get(i).getAcciones() : "")
											+ "\" ,  ");
					response.getWriter().println(
							" \"EstExp\":\"" + lista.get(i).getEstexp()
									+ "\" ,  ");
					response.getWriter().println(
							" \"c1\":\""
									+ (lista.get(i).getC1() != null ? lista
											.get(i).getC1() : "") + "\" ,  ");
					response.getWriter().println(
							" \"c2\":\""
									+ (lista.get(i).getC2() != null ? lista
											.get(i).getC2() : "") + "\" ,  ");
					response.getWriter().println(
							" \"c3\":\""
									+ (lista.get(i).getC3() != null ? lista
											.get(i).getC3() : "") + "\" ,  ");
					response.getWriter().println(
							" \"c4\":\""
									+ (lista.get(i).getC4() != null ? lista
											.get(i).getC4() : "") + "\" ,  ");
					response.getWriter().println(
							" \"c5\":\""
									+ (lista.get(i).getC5() != null ? lista
											.get(i).getC5() : "") + "\" ,  ");
					response.getWriter().println(
							" \"c6\":\""
									+ (lista.get(i).getC6() != null ? lista
											.get(i).getC6() : "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"Derivacion\":\""
											+ (lista.get(i).getDerivacion() != null ? lista
													.get(i).getDerivacion()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"DerivaDe\":\""
											+ (lista.get(i).getDerivaDe() != null ? lista
													.get(i).getDerivaDe() : "")
											+ "\" ,  ");

					response.getWriter().println(
							" \"EstOp\":\"" + lista.get(i).getEstop() + "\" ");
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

	/*
	 * @RequestMapping(value = "/formexpedientes/cargar.htm", method =
	 * RequestMethod.POST) public String cargarPOST(HttpServletRequest request,
	 * HttpServletResponse response) { logger.debug(" __cargarPOST ");
	 * 
	 * try { response.setContentType("application/json;charset=ISO-8859-1");
	 * request.setCharacterEncoding("UTF-8");
	 * 
	 * Integer offset = request.getParameter("offset") != null ? Integer
	 * .parseInt(request.getParameter("offset")) : 0; Integer size =
	 * request.getParameter("size") != null ? Integer
	 * .parseInt(request.getParameter("size")) : 15;
	 * 
	 * String campo = request.getParameter("campo") != null ? request
	 * .getParameter("campo") : ""; String valor = request.getParameter("valor")
	 * != null ? request .getParameter("valor") : "";
	 * 
	 * List<Expediente> lista; if (request.getParameter("demuna") == null) {
	 * lista = expedienteService.listarExpedientes(campo, valor); } else { lista
	 * = expedienteService.listarExpedientes(Integer
	 * .parseInt(request.getParameter("demuna"))); }
	 * 
	 * response.getWriter().println("{");
	 * response.getWriter().println(" \"success\":\"true\", ");
	 * response.getWriter().println( " \"total\":\"" + lista.size() + "\" , ");
	 * response.getWriter().println(" dataEspec:[ ");
	 * 
	 * Integer time = 0; for (int i = 0; i < lista.size(); i++) { if (i >=
	 * offset) { time++; response.getWriter().println("{ ");
	 * response.getWriter() .println( " \"KyExpediente\":\"" +
	 * lista.get(i).getKyexpediente() + "\" ,  "); response.getWriter().println(
	 * " \"KyDependencia\":\"" + (lista.get(i).getDemuna() != null ? lista
	 * .get(i).getDemuna().getKydemuna() : "") + "\" ,  ");
	 * response.getWriter().println( " \"NroExpediente\":\"" +
	 * lista.get(i).getNroexpediente() + "\" ,  "); response.getWriter()
	 * .println( " \"Fecha\":\"" + (lista.get(i).getFecha() != null ? lista
	 * .get(i).getFecha() : "") + "\" ,  "); response.getWriter() .println(
	 * " \"Materia\":\"" + (lista.get(i).getMateria() != null ? lista
	 * .get(i).getMateria() .getKymateria() : "") + "\" ,  ");
	 * response.getWriter() .println( " \"Lugar\":\"" + (lista.get(i).getLugar()
	 * != null ? lista .get(i).getLugar() : "") + "\" ,  ");
	 * response.getWriter() .println( " \"ResumenHechos\":\"" +
	 * (lista.get(i).getResumenhechos() != null ? lista
	 * .get(i).getResumenhechos() : "") + "\" ,  "); response.getWriter()
	 * .println( " \"Acciones\":\"" + (lista.get(i).getAcciones() != null ?
	 * lista .get(i).getAcciones() : "") + "\" ,  ");
	 * response.getWriter().println( " \"EstExp\":\"" + lista.get(i).getEstexp()
	 * + "\" ,  "); response.getWriter().println( " \"c1\":\"" +
	 * (lista.get(i).getC1() != null ? lista .get(i).getC1() : "") + "\" ,  ");
	 * response.getWriter().println( " \"c2\":\"" + (lista.get(i).getC2() !=
	 * null ? lista .get(i).getC2() : "") + "\" ,  ");
	 * response.getWriter().println( " \"c3\":\"" + (lista.get(i).getC3() !=
	 * null ? lista .get(i).getC3() : "") + "\" ,  ");
	 * response.getWriter().println( " \"c4\":\"" + (lista.get(i).getC4() !=
	 * null ? lista .get(i).getC4() : "") + "\" ,  ");
	 * response.getWriter().println( " \"c5\":\"" + (lista.get(i).getC5() !=
	 * null ? lista .get(i).getC5() : "") + "\" ,  ");
	 * response.getWriter().println( " \"c6\":\"" + (lista.get(i).getC6() !=
	 * null ? lista .get(i).getC6() : "") + "\" ,  "); response.getWriter()
	 * .println( " \"Derivacion\":\"" + (lista.get(i).getDerivacion() != null ?
	 * lista .get(i).getDerivacion() : "") + "\" ,  ");
	 * response.getWriter().println( " \"EstOp\":\"" + lista.get(i).getEstop() +
	 * "\" "); response.getWriter().println("}");
	 * 
	 * if (time == size) { break; } else { if ((i + 1) < lista.size()) {
	 * response.getWriter().println(","); } } } }
	 * 
	 * response.getWriter().println(" ] "); response.getWriter().println(" }");
	 * 
	 * } catch (Exception e) { try { response.getWriter() .println(
	 * "{\"success\": false, \"errors\":{\"reason\": \"No se pudo agregar el registro.\"}}"
	 * ); } catch (Exception e2) { e2.printStackTrace(); } e.printStackTrace();
	 * } return null; }
	 */
	@RequestMapping(value = "/formexpedientes/nuevoexpediente.htm", method = RequestMethod.POST)
	public String nuevoexpediente(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			String nroexpediente = "000-000000";
			Integer kydependencia = Integer.parseInt(request
					.getParameter("KyDependencia"));
			Integer kymateria = 1;

			Expediente expediente = new Expediente();
			expediente.setNroexpediente(nroexpediente);
			expediente.setDemuna(new Demuna());
			expediente.getDemuna().setKydemuna(kydependencia);
			expediente.setMateria(new Materia());
			expediente.getMateria().setKymateria(kymateria);
			expediente.setEstexp(0);
			expediente.setEstop(0);

			expedienteService.crearExpediente(expediente);

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

	@RequestMapping(value = "/formexpedientes/nuevo.htm", method = RequestMethod.POST)
	public String nuevo(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");
			logger.debug(" __nuevoPOST ");

			Long kyexpediente = Long.parseLong(request
					.getParameter("txtKyExpediente"));
			Integer kydependencia = Integer.parseInt(request
					.getParameter("txtdependencia"));
			String nroexpediente = request.getParameter("TxtNroExpediente") != null ? request
					.getParameter("TxtNroExpediente") : "";
			String lugar = request.getParameter("txtLugar") != null ? request
					.getParameter("txtLugar") : "";
			String fecha = request.getParameter("txtFecha") != null ? request
					.getParameter("txtFecha") : "";
			Integer kymateria = request.getParameter("combomateriaid") != null ? Integer
					.parseInt(request.getParameter("combomateriaid")) : 0;
			String resumen = request.getParameter("TxtResumen") != null ? request
					.getParameter("TxtResumen") : "";
			String acciones = request.getParameter("TxtAcciones") != null ? request
					.getParameter("TxtAcciones") : "";
			Integer estado = Integer.parseInt(request.getParameter("txtestado")
					.substring(0, 1));
			String derivacion = request.getParameter("cboDerivacion") != null ? request
					.getParameter("cboDerivacion") : "";
			String derivade = request.getParameter("txtDerivaDe") != null ? request
					.getParameter("txtDerivaDe") : "";
			String c1 = request.getParameter("chk01") != null ? request
					.getParameter("chk01") : "";
			String c2 = request.getParameter("chk02") != null ? request
					.getParameter("chk02") : "";
			String c3 = request.getParameter("chk03") != null ? request
					.getParameter("chk03") : "";
			String c4 = request.getParameter("chk04") != null ? request
					.getParameter("chk04") : "";
			String c5 = request.getParameter("chk05") != null ? request
					.getParameter("chk05") : "";
			String c6 = request.getParameter("chk06") != null ? request
					.getParameter("chk06") : "";

			Expediente expediente = new Expediente();
			logger.debug(" after get object ");

			expediente.setKyexpediente(kyexpediente);
			expediente.setDemuna(new Demuna());
			expediente.getDemuna().setKydemuna(kydependencia);

			expediente.setNroexpediente(nroexpediente);
			expediente.setLugar(lugar);
			expediente.setFecha(fecha);
			if (kymateria != 0) {
				expediente.setMateria(new Materia());
				expediente.getMateria().setKymateria(kymateria);
			}
			expediente.setResumenhechos(resumen);
			expediente.setAcciones(acciones);
			expediente.setEstexp(estado);
			expediente.setDerivacion(derivacion);
			expediente.setDerivaDe(derivade);
			expediente.setC1(c1);
			expediente.setC2(c2);
			expediente.setC3(c3);
			expediente.setC4(c4);
			expediente.setC5(c5);
			expediente.setC6(c6);

			expedienteService.actualizarExpediente(expediente);

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

	@RequestMapping(value = "/formexpedientes/eliminar.htm", method = RequestMethod.POST)
	public String eliminarExpediente(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			logger.debug(" __eliminarExpediente ");
			Integer kyexpediente = Integer.parseInt(request.getParameter("id"));

			expedienteService.eliminarExpediente(kyexpediente);

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

	@RequestMapping(value = "/formexpedientes/cargarDiscapacidad.htm")
	public String cargarDiscapacidad(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug(" cargarDiscapacidad ");

		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			List<Discapacidad> lista;
			if (request.getParameter("estado") == null) {
				lista = discapacidadService.listarDiscapacidades();
			} else {
				lista = discapacidadService.listarDiscapacidades(Integer
						.parseInt(request.getParameter("estado")));
			}

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" data:[ ");
			for (int i = 0; i < lista.size(); i++) {
				response.getWriter().println("{ ");
				response.getWriter().println(
						" \"KyDiscapacidad\":\""
								+ lista.get(i).getKydiscapacidad() + "\" ,  ");
				response.getWriter().println(
						" \"Discapacidad\":\"" + lista.get(i).getDiscapacidad()
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

	// region Informante
	@RequestMapping(value = "/formexpedientes/cargarinformantes.htm")
	public String cargarinformantes(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug(" cargarInformantes ");
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;
			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;

			List<ExpedientesInformante> lista = expedienteInformanteService
					.obtenerPorExpediente(Integer.parseInt(request
							.getParameter("idexpediente")));

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" dataEspec:[ ");
			Integer time = 0;
			for (int i = 0; i < lista.size(); i++) {
				if (i >= offset) {
					time++;

					response.getWriter().println("{ ");
					response.getWriter()
							.println(
									" \"KyInformante\":\""
											+ lista.get(i).getKyinformante()
											+ "\" ,  ");
					response.getWriter().println(
							" \"Dni\":\""
									+ (lista.get(i).getDni() != null ? lista
											.get(i).getDni() : "") + "\" ,  ");
					response.getWriter().println(
							" \"Nombre\":\""
									+ (lista.get(i).getNombre() != null ? lista
											.get(i).getNombre() : "")
									+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Nombres\":\""
											+ (lista.get(i).getNombres() != null ? lista
													.get(i).getNombres() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"ApPaterno\":\""
											+ (lista.get(i).getApPaterno() != null ? lista
													.get(i).getApPaterno() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"ApMaterno\":\""
											+ (lista.get(i).getApMaterno() != null ? lista
													.get(i).getApMaterno() : "")
											+ "\" ,  ");
					response.getWriter().println(
							" \"Edad\":\"" + lista.get(i).getEdad() + "\" ,  ");
					response.getWriter().println(
							" \"Sexo\":\""
									+ (lista.get(i).getSexo() != null ? lista
											.get(i).getSexo() : "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"Domicilio\":\""
											+ (lista.get(i).getDomicilio() != null ? lista
													.get(i).getDomicilio() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Telefono\":\""
											+ (lista.get(i).getTelefono() != null ? lista
													.get(i).getTelefono() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Ocupacion\":\""
											+ (lista.get(i).getOcupacion() != null ? lista
													.get(i).getOcupacion() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Relacion_Afectado\":\""
											+ (lista.get(i)
													.getRelacionAfectado() != null ? lista
													.get(i)
													.getRelacionAfectado() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"KyDepartamento\":\""
											+ (lista.get(i).getKydepartamento() != null ? lista
													.get(i).getKydepartamento()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"EstCivil\":\""
											+ (lista.get(i).getEstcivil() != null ? lista
													.get(i).getEstcivil() : "")
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

	@RequestMapping(value = "/formexpedientes/newinformante.htm", method = RequestMethod.POST)
	public String nuevoInformante(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			logger.debug(" newInformante ");
			Long kyexpediente = Long.parseLong(request
					.getParameter("KyExpediente"));

			ExpedientesInformante ei = new ExpedientesInformante();
			ei.setExpediente(new Expediente());
			ei.getExpediente().setKyexpediente(kyexpediente);
			ei.setEdad(0);
			ei.setEstado(1);

			expedienteInformanteService.registrarExpedienteInformante(ei);

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

	@RequestMapping(value = "/formexpedientes/delinformante.htm", method = RequestMethod.POST)
	public String eliminarInformante(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			logger.debug(" __eliminarInformante ");
			Long kyInformante = Long.parseLong(request
					.getParameter("KyInformante"));

			expedienteInformanteService.eliminarInformante(kyInformante);

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

	@RequestMapping(value = "/formexpedientes/informantes.htm", method = RequestMethod.POST)
	public String actualizarInformante(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			logger.debug(" __nItemInformante ");
			Long kyinformante = Long.parseLong(request
					.getParameter("nItemInformante"));
			Integer dni = (request.getParameter("Dni") != null ? Integer
					.parseInt(request.getParameter("Dni")) : 0);
			String domicilio = (request.getParameter("Domicilio") != null ? request
					.getParameter("Domicilio") : "");
			Integer edad = Integer.parseInt(request.getParameter("Edad"));
			String departamento = (request.getParameter("KyDpto") != null ? request
					.getParameter("KyDpto") : "");
			String nombres = (request.getParameter("Nombres") != null ? request
					.getParameter("Nombres") : "");
			String appaterno = (request.getParameter("ApPaterno") != null ? request
					.getParameter("ApPaterno") : "");
			String apmaterno = (request.getParameter("ApMaterno") != null ? request
					.getParameter("ApMaterno") : "");

			String ocupacion = (request.getParameter("Ocupacion") != null ? request
					.getParameter("Ocupacion") : "");
			String relacion = (request.getParameter("Relacion") != null ? request
					.getParameter("Relacion") : "");
			String sexo = (request.getParameter("Sexo") != null ? request
					.getParameter("Sexo") : "");
			String telefono = (request.getParameter("Telefono") != null ? request
					.getParameter("Telefono") : "");
			String estcivil = (request.getParameter("EstCivil") != null ? request
					.getParameter("EstCivil") : "");

			ExpedientesInformante ei = new ExpedientesInformante();
			ei.setKyinformante(kyinformante);
			if (dni > 0)
				ei.setDni(String.format("%08d", dni));
			else
				ei.setDni("");
			ei.setDomicilio(domicilio);
			ei.setEdad(edad);
			ei.setKydepartamento(departamento);
			ei.setNombre(nombres + " " + appaterno + " " + apmaterno);
			ei.setNombres(nombres);
			ei.setApPaterno(appaterno);
			ei.setApMaterno(apmaterno);
			ei.setOcupacion(ocupacion);
			ei.setRelacionAfectado(relacion);
			ei.setTelefono(telefono);
			ei.setSexo(sexo);
			ei.setEstcivil(estcivil);

			expedienteInformanteService.actualizarExpedienteInformante(ei);

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

	// endregion
	// region Afectado
	@RequestMapping(value = "/formexpedientes/newafectado.htm", method = RequestMethod.POST)
	public String nuevoAfectado(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			logger.debug(" newAfectado: "
					+ request.getParameter("KyExpediente"));

			Long kyexpediente = Long.parseLong(request
					.getParameter("KyExpediente"));

			ExpedientesAfectado et = new ExpedientesAfectado();
			et.setExpediente(new Expediente());
			et.getExpediente().setKyexpediente(kyexpediente);
			et.setDiscapacidad("NO");
			et.setMadresoltera("NO");
			et.setEdad(0);
			et.setEstado(1);

			expedienteAfectadoService.registrarExpedienteAfectado(et);

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

	@RequestMapping(value = "/formexpedientes/afectados.htm", method = RequestMethod.POST)
	public String actualizarAfectado(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			logger.debug(" __actualizarAfectados ");
			Long kyafectado = Long.parseLong(request
					.getParameter("nItemAfectado"));

			String discapacidad = (request.getParameter("Discapacidad") != null ? request
					.getParameter("Discapacidad") : "");
			Integer dni = (request.getParameter("Dni") != null ? Integer
					.parseInt(request.getParameter("Dni")) : 0);
			String domicilio = (request.getParameter("Domicilio") != null ? request
					.getParameter("Domicilio") : "");
			Integer edad = Integer.parseInt(request.getParameter("Edad"));
			String estadocivil = (request.getParameter("EstCivil") != null ? request
					.getParameter("EstCivil") : "");
			String departamento = (request.getParameter("KyDpto") != null ? request
					.getParameter("KyDpto") : "");
			String madre = (request.getParameter("Madre") != null ? request
					.getParameter("Madre") : "");
			String nombres = (request.getParameter("Nombres") != null ? request
					.getParameter("Nombres") : "");
			String appaterno = (request.getParameter("ApPaterno") != null ? request
					.getParameter("ApPaterno") : "");
			String apmaterno = (request.getParameter("ApMaterno") != null ? request
					.getParameter("ApMaterno") : "");
			String sexo = (request.getParameter("Sexo") != null ? request
					.getParameter("Sexo") : "");

			ExpedientesAfectado ea = new ExpedientesAfectado();
			ea.setKyafectado(kyafectado);
			ea.setDiscapacidad(discapacidad);
			if (dni > 0)
				ea.setDni(String.format("%08d", dni));
			else
				ea.setDni("");
			ea.setDomicilio(domicilio);
			ea.setEdad(edad);
			ea.setEstcivil(estadocivil);
			ea.setKydepartamento(departamento);
			ea.setMadresoltera(madre);
			ea.setNombre(nombres + " " + appaterno + " " + apmaterno);
			ea.setNombres(nombres);
			ea.setApPaterno(appaterno);
			ea.setApMaterno(apmaterno);
			ea.setSexo(sexo);

			expedienteAfectadoService.actualizarExpedienteAfectado(ea);

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

	@RequestMapping(value = "/formexpedientes/delafectado.htm", method = RequestMethod.POST)
	public String eliminarAfectado(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			logger.debug(" __eliminarAfectado ");
			Long kyafectado = Long
					.parseLong(request.getParameter("KyAfectado"));

			expedienteAfectadoService.eliminarAfectado(kyafectado);

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

	@RequestMapping(value = "/formexpedientes/cargarafectados.htm")
	public String cargarafectados(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug(" cargarAfectados ");
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;
			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;

			List<ExpedientesAfectado> lista = expedienteAfectadoService
					.obtenerPorExpediente(Integer.parseInt(request
							.getParameter("idexpediente")));

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" dataEspec:[ ");
			Integer time = 0;
			for (int i = 0; i < lista.size(); i++) {
				if (i >= offset) {
					time++;

					response.getWriter().println("{ ");
					response.getWriter().println(
							" \"KyAfectado\":\"" + lista.get(i).getKyafectado()
									+ "\" ,  ");
					response.getWriter().println(
							" \"Dni\":\""
									+ (lista.get(i).getDni() != null ? lista
											.get(i).getDni() : "") + "\" ,  ");
					response.getWriter().println(
							" \"Nombre\":\""
									+ (lista.get(i).getNombre() != null ? lista
											.get(i).getNombre() : "")
									+ "\" ,  ");

					response.getWriter()
							.println(
									" \"Nombres\":\""
											+ (lista.get(i).getNombres() != null ? lista
													.get(i).getNombres() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"ApPaterno\":\""
											+ (lista.get(i).getApPaterno() != null ? lista
													.get(i).getApPaterno() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"ApMaterno\":\""
											+ (lista.get(i).getApMaterno() != null ? lista
													.get(i).getApMaterno() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Domicilio\":\""
											+ (lista.get(i).getDomicilio() != null ? lista
													.get(i).getDomicilio() : "")
											+ "\" ,  ");
					response.getWriter().println(
							" \"Edad\":\"" + lista.get(i).getEdad() + "\" ,  ");
					response.getWriter().println(
							" \"Sexo\":\""
									+ (lista.get(i).getSexo() != null ? lista
											.get(i).getSexo() : "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"KyDepartamento\":\""
											+ (lista.get(i).getKydepartamento() != null ? lista
													.get(i).getKydepartamento()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"Discapacidad\":\""
											+ (lista.get(i).getDiscapacidad() != null ? lista
													.get(i).getDiscapacidad()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"MadreSoltera\":\""
											+ (lista.get(i).getMadresoltera() != null ? lista
													.get(i).getMadresoltera()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"EstCivil\":\""
											+ (lista.get(i).getEstcivil() != null ? lista
													.get(i).getEstcivil() : "")
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

	// endregion
	// region Transgresor
	@RequestMapping(value = "/formexpedientes/cargartransgresor.htm", method = RequestMethod.POST)
	public String cargarTransgresor(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug(" cargarTransgresor ");

		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;
			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;

			List<ExpedientesTransgresor> lista = expedienteTransgresorService
					.obtenerPorExpediente(Integer.parseInt(request
							.getParameter("idexpediente")));

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(
					" \"total\":\"" + lista.size() + "\" , ");
			response.getWriter().println(" dataEspec:[ ");
			Integer time = 0;
			for (int i = 0; i < lista.size(); i++) {
				if (i >= offset) {
					time++;

					response.getWriter().println("{ ");
					response.getWriter().println(
							" \"KyTransgresor\":\""
									+ lista.get(i).getKytransgresor()
									+ "\" ,  ");
					response.getWriter().println(
							" \"Dni\":\""
									+ (lista.get(i).getDni() != null ? lista
											.get(i).getDni() : "") + "\" ,  ");
					response.getWriter().println(
							" \"Nombre\":\""
									+ (lista.get(i).getNombre() != null ? lista
											.get(i).getNombre() : "")
									+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Nombres\":\""
											+ (lista.get(i).getNombres() != null ? lista
													.get(i).getNombres() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"ApPaterno\":\""
											+ (lista.get(i).getApPaterno() != null ? lista
													.get(i).getApPaterno() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"ApMaterno\":\""
											+ (lista.get(i).getApMaterno() != null ? lista
													.get(i).getApMaterno() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Domicilio\":\""
											+ (lista.get(i).getDomicilio() != null ? lista
													.get(i).getDomicilio() : "")
											+ "\" ,  ");
					response.getWriter().println(
							" \"Edad\":\"" + lista.get(i).getEdad() + "\" ,  ");
					response.getWriter().println(
							" \"Sexo\":\""
									+ (lista.get(i).getSexo() != null ? lista
											.get(i).getSexo() : "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"KyDepartamento\":\""
											+ (lista.get(i).getKydepartamento() != null ? lista
													.get(i).getKydepartamento()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"Ocupacion\":\""
											+ (lista.get(i).getOcupacion() != null ? lista
													.get(i).getOcupacion() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Relacion\":\""
											+ (lista.get(i).getRelacion() != null ? lista
													.get(i).getRelacion() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"EstCivil\":\""
											+ (lista.get(i).getEstcivil() != null ? lista
													.get(i).getEstcivil() : "")
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

	@RequestMapping(value = "/formexpedientes/newtransgresor.htm", method = RequestMethod.POST)
	public String nuevoTransgresor(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			logger.debug(" newTransgresor ");
			Long kyexpediente = Long.parseLong(request
					.getParameter("KyExpediente"));

			ExpedientesTransgresor et = new ExpedientesTransgresor();
			et.setExpediente(new Expediente());
			et.getExpediente().setKyexpediente(kyexpediente);
			et.setEdad(0);
			et.setEstado(1);

			expedienteTransgresorService.registrarExpedienteTransgresor(et);

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

	@RequestMapping(value = "/formexpedientes/trangresor.htm", method = RequestMethod.POST)
	public String actualizarTransgresor(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			logger.debug(" __actualizarTransgresor ");
			Long kytransgresor = Long.parseLong(request
					.getParameter("nItemTransgresor"));
			Integer dni = (request.getParameter("Dni") != null ? Integer
					.parseInt(request.getParameter("Dni")) : 0);
			String domicilio = (request.getParameter("Domicilio") != null ? request
					.getParameter("Domicilio") : "");
			Integer edad = Integer.parseInt(request.getParameter("Edad"));
			String departamento = (request.getParameter("KyDpto") != null ? request
					.getParameter("KyDpto") : "");
			String nombres = (request.getParameter("Nombres") != null ? request
					.getParameter("Nombres") : "");
			String appaterno = (request.getParameter("ApPaterno") != null ? request
					.getParameter("ApPaterno") : "");
			String apmaterno = (request.getParameter("ApMaterno") != null ? request
					.getParameter("ApMaterno") : "");
			String ocupacion = (request.getParameter("Ocupacion") != null ? request
					.getParameter("Ocupacion") : "");
			String relacion = (request.getParameter("Relacion") != null ? request
					.getParameter("Relacion") : "");
			String sexo = (request.getParameter("Sexo") != null ? request
					.getParameter("Sexo") : "");
			String estcivil = (request.getParameter("EstCivil") != null ? request
					.getParameter("EstCivil") : "");

			ExpedientesTransgresor et = new ExpedientesTransgresor();
			et.setKytransgresor(kytransgresor);
			if (dni > 0)
				et.setDni(String.format("%08d", dni));
			else
				et.setDni("");
			et.setDomicilio(domicilio);
			et.setEdad(edad);
			et.setKydepartamento(departamento);
			et.setNombre(nombres + " " + appaterno + " " + apmaterno);
			et.setNombres(nombres);
			et.setApPaterno(appaterno);
			et.setApMaterno(apmaterno);
			et.setOcupacion(ocupacion);
			et.setRelacion(relacion);
			et.setEstcivil(estcivil);
			et.setSexo(sexo);

			expedienteTransgresorService.actualizarExpedienteTransgresor(et);

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

	@RequestMapping(value = "/formexpedientes/deltransgresor.htm", method = RequestMethod.POST)
	public String eliminarTransgresor(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			logger.debug(" __eliminarTransgresor ");
			Long kytransgresor = Long.parseLong(request
					.getParameter("KyTransgresor"));

			expedienteTransgresorService.eliminarTransgresor(kytransgresor);

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

	// endregion

	@RequestMapping(value = "/formexpedientes/setUbigeo.htm", method = RequestMethod.POST)
	public String setUbigeo(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			Long kytabla = Long.parseLong(request.getParameter("KyTabla"));
			String ubigeo = request.getParameter("KyDpto");
			String tabla = request.getParameter("Tabla");
			if (tabla.equals("Informantes")) {
				ExpedientesInformante obj = new ExpedientesInformante();
				obj.setKyinformante(kytabla);
				obj.setKydepartamento(ubigeo);
				expedienteInformanteService.actualizarExpedienteInformante(obj);
			}
			if (tabla.equals("Afectados")) {
				ExpedientesAfectado ea = new ExpedientesAfectado();
				ea.setKyafectado(kytabla);
				ea.setKydepartamento(ubigeo);
				expedienteAfectadoService.actualizarExpedienteAfectado(ea);
			}
			if (tabla.equals("Transgresores")) {
				ExpedientesTransgresor et = new ExpedientesTransgresor();
				et.setKytransgresor(kytabla);
				et.setKydepartamento(ubigeo);
				expedienteTransgresorService
						.actualizarExpedienteTransgresor(et);
			}
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

	@RequestMapping(value = "/formexpedientes/cargarMaterias.htm", method = RequestMethod.POST)
	public String cargarMaterias(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug(" cargarMaterias ");

		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;
			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 500;

			List<Materia> lista = materiaService.listarMaterias(Integer
					.parseInt(request.getParameter("estado")));

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
							" \"KyMateria\":\"" + lista.get(i).getKymateria()
									+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Materia\":\""
											+ lista.get(i).getKymateria()
											+ " . "
											+ (lista.get(i).getMateria() != null ? lista
													.get(i).getMateria() : "")
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

	@RequestMapping(value = "/formexpedientes/cargarmateriasexpediente.htm")
	public String cargarmateriasexpediente(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug(" cargarmateriasexpediente ");

		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			Integer offset = request.getParameter("offset") != null ? Integer
					.parseInt(request.getParameter("offset")) : 0;
			Integer size = request.getParameter("size") != null ? Integer
					.parseInt(request.getParameter("size")) : 15;

			List<ExpedientesMateria> lista = expedienteMateriaService
					.obtenerPorExpediente(Integer.parseInt(request
							.getParameter("idexpediente")));

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
							" \"KyExpedienteMateria\":\""
									+ lista.get(i).getKyexpedientemateria()
									+ "\" ,  ");
					response.getWriter().println(
							" \"KyExpediente\":\""
									+ (lista.get(i).getDemuna() != null ? lista
											.get(i).getDemuna().getKydemuna()
											: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"KyMateria\":\""
											+ (lista.get(i).getMateria() != null ? lista
													.get(i).getMateria()
													.getKymateria()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"Conciliable\":\""
											+ (lista.get(i).getMateria() != null ? lista
													.get(i).getMateria()
													.getConciliable()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"Materia\":\""
											+ (lista.get(i).getMateria() != null ? lista
													.get(i).getMateria()
													.getMateria()
													: "") + "\" ");
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

	@RequestMapping(value = "/formexpedientes/delmateria.htm", method = RequestMethod.POST)
	public String eliminarMateria(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			logger.debug(" __eliminarMateria ");
			Long KyExpedienteMateria = Long.parseLong(request
					.getParameter("KyExpedienteMateria"));

			expedienteMateriaService.eliminarMateria(KyExpedienteMateria);

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

	@RequestMapping(value = "/formexpedientes/newmateria.htm", method = RequestMethod.POST)
	public String nuevoMateria(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			logger.debug(" newMateria ");
			Long kyexpediente = Long.parseLong(request
					.getParameter("KyExpediente"));
			Integer kymateria = Integer.parseInt(request
					.getParameter("KyMateria"));
			Integer kydemuna = Integer.parseInt(request
					.getParameter("KyDependencia"));

			ExpedientesMateria em = new ExpedientesMateria();
			em.setExpediente(new Expediente());
			em.getExpediente().setKyexpediente(kyexpediente);
			em.setMateria(new Materia());
			em.getMateria().setKymateria(kymateria);
			em.setDemuna(new Demuna());
			em.getDemuna().setKydemuna(kydemuna);

			expedienteMateriaService.registrarExpedienteMateria(em);

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

	@RequestMapping(value = "/formexpedientes/cargarpais.htm")
	public String cargarpais(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(" \"total\":\"0\" , ");
			response.getWriter().println(" data:[ ");
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

	@RequestMapping(value = "/formexpedientes/cargarciudad.htm")
	public String cargarciudad(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");

			response.getWriter().println("{");
			response.getWriter().println(" \"success\":\"true\", ");
			response.getWriter().println(" \"total\":\"0\" , ");
			response.getWriter().println(" data:[ ");
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
}
