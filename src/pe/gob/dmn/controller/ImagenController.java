package pe.gob.dmn.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.gob.dmn.model.Demuna;
import pe.gob.dmn.model.FileUploadBean;
import pe.gob.dmn.service.DemunaService;

@Controller
@Scope("session")
public class ImagenController {

	private static final Logger logger = Logger
			.getLogger(ImagenController.class);

	@Autowired
	private DemunaService demunaService;

	@RequestMapping(value = "/cargarimagen.htm", method = RequestMethod.POST)
	public String aaaexpedienteprint(FileUploadBean uploadItem,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			Integer kydemuna = Integer.parseInt(uploadItem.getTxtId2());

			if (kydemuna > 0) {
				if (uploadItem.getFile().getSize() <= 1048576 * 10) {
					Demuna dem = new Demuna();
					dem.setImagen(uploadItem.getFile().getOriginalFilename());
					dem.setKydemuna(kydemuna);
					dem.setArchivo(uploadItem.getFile().getBytes());

					demunaService.actualizarImagen(dem);

					response.getWriter().println("{");
					response.getWriter().println(" \"success\":\"true\" ");
					response.getWriter().println(
							" , \"msg\": \"Archivo cargado.\"}");
				} else {
					response.getWriter().println("{");
					response.getWriter().println(" \"success\":\"false\" ");
					response.getWriter()
							.println(
									" , \"errors\": \"No se puede subir el archivo. Es demasiado grande, máximo 5MB.\"}");
				}
			} else {
				response.getWriter().println("{");
				response.getWriter().println(" \"success\":\"false\" ");
				response.getWriter()
						.println(
								" , \"errors\": \"No se puede subir el archivo. No ha seleccionado ningun elemento.\"}");
			}
		} catch (Exception e) {
			try {
				response.getWriter().println(
						"{\"success\": \"false\", \"errors\": \"No se pudo subir el archivo. "
								+ e.getMessage() + "\"}");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/descargarimagen.htm")
	public String descargar(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("obtenog imagen");
		try {

			Integer id = Integer.parseInt(request.getParameter("id"));
			Demuna dem = demunaService.obtener(id);

			response.reset();
			response.setContentType("image/jpg");
			ServletOutputStream out = response.getOutputStream();
			out.write(dem.getArchivo(), 0, dem.getArchivo().length);
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
