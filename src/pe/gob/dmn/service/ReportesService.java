package pe.gob.dmn.service;

import pe.gob.dmn.model.ReporteDemunasMeses;
import pe.gob.dmn.model.ReporteDemunasMesesMaterias;
import pe.gob.dmn.model.ReporteDemunasTodo;

import java.util.List;

public interface ReportesService {
	
	public List<ReporteDemunasTodo> getReporteDemunasTodo();

	List<ReporteDemunasTodo> getReporteDemunasTodoPorFecha(String ini, String fin);

	List<ReporteDemunasMeses> getReporteDemunasMeses(Integer kydemuna);

	public List<ReporteDemunasMesesMaterias> 
	getReporteDemunasMesesMaterias(Integer kydemuna);

	public List<ReporteDemunasMesesMaterias> 
	getReporteDemunasGraficoFecha(Integer kydemuna, String iniDateString, String finDateString);

	List<ReporteDemunasMesesMaterias> 
	getReporteDemunasGrafico(Integer kydemuna);

	public List<ReporteDemunasMesesMaterias> 
	getReporteDemunasMesesMateriasPorMes(Integer kydemuna, String string);
}
