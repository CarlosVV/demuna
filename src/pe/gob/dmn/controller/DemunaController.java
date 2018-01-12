package pe.gob.dmn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.gob.dmn.model.Demuna;
import pe.gob.dmn.model.DemunaNivel;
import pe.gob.dmn.service.DemunaService;

@Controller
@Scope("session")
public class DemunaController {

	private static final Logger logger = Logger
			.getLogger(DemunaController.class);

	@Autowired
	private DemunaService demunaService;

	@RequestMapping(value = "/formdemunas/cargar.htm")
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

			List<Demuna> lista = demunaService.listarDemunas();

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
							" \"KyDemuna\":\"" + lista.get(i).getKydemuna()
									+ "\" ,  ");
					response.getWriter().println(
							" \"MimdesNro\":\"" + lista.get(i).getMimdesnro()
									+ "\" ,  ");
					response.getWriter().println(
							" \"Nombre\":\"" + lista.get(i).getNombre()
									+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Ubicacion\":\""
											+ (lista.get(i).getUbicacion() != null ? lista
													.get(i).getUbicacion() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Direccion\":\""
											+ (lista.get(i).getDireccion() != null ? lista
													.get(i).getDireccion() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Referencia\":\""
											+ (lista.get(i).getReferencia() != null ? lista
													.get(i).getReferencia()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"Telefono\":\""
											+ (lista.get(i).getTelefono() != null ? lista
													.get(i).getTelefono() : "")
											+ "\" ,  ");
					response.getWriter().println(
							" \"Correo\":\""
									+ (lista.get(i).getCorreo() != null ? lista
											.get(i).getCorreo() : "")
									+ "\" ,  ");
					response.getWriter()
							.println(
									" \"FechaCreacion\":\""
											+ (lista.get(i).getFechacreacion() != null ? lista
													.get(i).getFechacreacion()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"ResolucionNro\":\""
											+ (lista.get(i).getResolucionnro() != null ? lista
													.get(i).getResolucionnro()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"Coordinador\":\""
											+ (lista.get(i).getCoordinador() != null ? lista
													.get(i).getCoordinador()
													: "") + "\" ,  ");
					response.getWriter()
							.println(
									" \"KyTipoDemuna\":\""
											+ (lista.get(i).getDemunaNivel() != null ? lista
													.get(i).getDemunaNivel()
													.getKytipodemuna()
													: "") + "\" ,  ");
					response.getWriter().println(
							" \"Dep1\":\""
									+ (lista.get(i).getDep1() != null ? lista
											.get(i).getDep1() : "") + "\" ,  ");
					response.getWriter().println(
							" \"Estado\":\"" + lista.get(i).getEstado()
									+ "\" ,  ");
					response.getWriter()
							.println(
									" \"Cecte\":\""
											+ (lista.get(i).getCecte() != null ? lista
													.get(i).getCecte() : "")
											+ "\" ,  ");
					response.getWriter()
							.println(
									" \"NroCecte\":\""
											+ (lista.get(i).getNrocecte() != null ? lista
													.get(i).getNrocecte() : "")
											+ "\"  ");
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

	@RequestMapping(value = "/formdemunas/editardemuna.htm")
	public String editardemuna(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.debug(" __editar_demuna ");
			Integer kyDemuna = Integer.parseInt(request
					.getParameter("txtKyDemuna"));
			String nroMimDes = request.getParameter("txtNroMimdes");
			String nombre = request.getParameter("txtNombre");
			String ubicacion = request.getParameter("txtUbicacion");
			String fecha = request.getParameter("txtFecha").toString().length() == 10 ? request
					.getParameter("txtFecha") : "";
			String referencia = request.getParameter("txtReferencia");
			String telefono = request.getParameter("txtTelefono");
			String correo = request.getParameter("txtCorreo");
			String direccion = request.getParameter("txtDireccion");
			String cordinador = request.getParameter("txtCordinador");
			String resolucion = request.getParameter("txtResolucion");
			Integer estado = Integer.parseInt(request.getParameter("txtEstado")
					.substring(0, 1));
			Integer nivel = Integer.parseInt(request.getParameter("txtNivel")
					.substring(0, 1));
			logger.debug(" __nivel: " + nivel);
			Integer dependencia = Integer.parseInt(request.getParameter(
					"txtDependencia").substring(0, 1));
			String nrocecte = request.getParameter("txtNroCecte");
			String conciliable = request.getParameter("cboSINO");

			Demuna dem = new Demuna();
			dem.setKydemuna(kyDemuna);
			dem.setMimdesnro(nroMimDes);
			dem.setNombre(nombre);
			dem.setUbicacion(ubicacion);
			dem.setFechacreacion(fecha);
			dem.setReferencia(referencia);
			dem.setTelefono(telefono);
			dem.setCorreo(correo);
			dem.setDireccion(direccion);
			dem.setCoordinador(cordinador);
			dem.setResolucionnro(resolucion);
			dem.setEstado(estado);
			dem.setDemunaNivel(new DemunaNivel());
			dem.getDemunaNivel().setKytipodemuna(nivel);
			dem.setDep1(dependencia);
			dem.setNrocecte(nrocecte);
			dem.setCecte(conciliable);

			demunaService.registrarDemuna(dem);

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

	@RequestMapping(value = "/formdemunas/nuevademuna.htm")
	public String nuevademuna(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Demuna dem = new Demuna();
			dem.setMimdesnro("000-0000-00000");
			dem.setNombre("Nueva Demuna...");
			dem.setEstado(0);
			demunaService.registrarDemuna(dem);

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

	@RequestMapping(value = "/formdemunas/eliminardemuna.htm")
	public String eliminardemuna(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Integer kyDemuna = Integer.parseInt(request
					.getParameter("KyDemuna"));
			demunaService.eliminarDemuna(kyDemuna);
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
