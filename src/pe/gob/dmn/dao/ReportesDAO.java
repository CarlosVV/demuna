package pe.gob.dmn.dao;

import java.util.List;

import pe.gob.dmn.model.ReporteDemunasMeses;
import pe.gob.dmn.model.ReporteDemunasMesesMaterias;
import pe.gob.dmn.model.ReporteDemunasTodo;

public interface ReportesDAO {

	public List<ReporteDemunasTodo> getReporteDemunasTodo();

	List<ReporteDemunasTodo> getReporteDemunasTodoPorFecha(String ini, String fin);

	List<ReporteDemunasMeses> getReporteDemunasMeses(Integer kydemuna);

	List<ReporteDemunasMesesMaterias> getReporteDemunasMesesMaterias(
			Integer kydemuna);

	List<ReporteDemunasMesesMaterias> getReporteDemunasGrafico(Integer kydemuna);

	List<ReporteDemunasMesesMaterias> getReporteDemunasGraficoFecha(
			Integer kydemuna, String ini, String fin);

	List<ReporteDemunasMesesMaterias> getReporteDemunasMesesMateriasPorMes(
			Integer kydemuna, String mes);
}
