package pe.gob.dmn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.dmn.dao.ReportesDAO;
import pe.gob.dmn.service.ReportesService;
import pe.gob.dmn.model.*;

@Service
public class ReportesServiceImpl implements ReportesService {

	@Autowired
	private ReportesDAO reportesDAO;
	
	@Override
	public List<ReporteDemunasTodo> getReporteDemunasTodo() {
		return reportesDAO.getReporteDemunasTodo();
	}
	
	@Override
	public List<ReporteDemunasTodo> getReporteDemunasTodoPorFecha(String ini, String fin){
		return reportesDAO.getReporteDemunasTodoPorFecha(ini,fin);
	}
	
	@Override
	public List<ReporteDemunasMeses> getReporteDemunasMeses(Integer kydemuna){
		return reportesDAO.getReporteDemunasMeses(kydemuna);
	}

	@Override
	public List<ReporteDemunasMesesMaterias> getReporteDemunasMesesMaterias(
			Integer kydemuna) {
		return reportesDAO.getReporteDemunasMesesMaterias(kydemuna);
	}

	@Override
	public List<ReporteDemunasMesesMaterias> getReporteDemunasGrafico(Integer kydemuna) {
		return reportesDAO.getReporteDemunasGrafico(kydemuna);
	}

	@Override
	public List<ReporteDemunasMesesMaterias> getReporteDemunasGraficoFecha(Integer kydemuna,
			String ini, String fin) {
		return reportesDAO.getReporteDemunasGraficoFecha(kydemuna,ini,fin);
	}

	@Override
	public List<ReporteDemunasMesesMaterias> getReporteDemunasMesesMateriasPorMes(
			Integer kydemuna, String mes) {
		return reportesDAO.getReporteDemunasMesesMateriasPorMes(kydemuna,mes);
	}
}
