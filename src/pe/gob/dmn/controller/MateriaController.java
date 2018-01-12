package pe.gob.dmn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.gob.dmn.model.Materia;
import pe.gob.dmn.service.MateriaService;

@Controller
@Scope("session")
public class MateriaController {

	private static final Logger logger = Logger
			.getLogger(MateriaController.class);

	@Autowired
	private MateriaService materiasService;

	@RequestMapping(value = "/formmaterias/cargar.htm")
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

			List<Materia> lista = materiasService.listarMaterias();
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
					response.getWriter().println(
							" \"Materia\":\"" + lista.get(i).getMateria()
									+ "\" ,  ");
					response.getWriter().println(
							" \"Conciliable\":\""
									+ lista.get(i).getConciliable() + "\" ,  ");
					response.getWriter()
							.println(
									" \"Estado\":\"" + lista.get(i).getEstmat()
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

	@RequestMapping(value = "/formmaterias/nuevamateria.htm")
	public String nuevamateria(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String materia = "Nueva Materia...";
			Integer estado = 0;
			String conciliable = "NO";

			Materia mat = new Materia();
			mat.setMateria(materia);
			mat.setEstmat(estado);
			mat.setConciliable(conciliable);
			materiasService.registrarMateria(mat);

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

	@RequestMapping(value = "/formmaterias/editarmateria.htm")
	public String editarmateria(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Integer kymateria = Integer.parseInt(request
					.getParameter("txtKyMateria"));
			String materia = request.getParameter("txtMateria");
			Integer estado = Integer.parseInt(request.getParameter("txtEstado")
					.substring(0, 1));
			String conciliable = request.getParameter("cboSINO");

			Materia mat = new Materia();
			mat.setKymateria(kymateria);
			mat.setMateria(materia);
			mat.setEstmat(estado);
			mat.setConciliable(conciliable);
			materiasService.registrarMateria(mat);

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

	@RequestMapping(value = "/formmaterias/eliminamateria.htm")
	public String eliminamateria(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Integer kymateria = Integer.parseInt(request
					.getParameter("KyMateria"));
			materiasService.eliminarMateria(kymateria);
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
