package pe.gob.dmn.model;

import java.io.Serializable;

/**
 * The persistent class for the DEMUNAS database table.
 * 
 */ 

public class ReporteDemunasMeses implements Serializable {
	private static final long serialVersionUID = 1L;


	
	private Long casos;
	
	private Integer kydependencia;

	private String demuna;
	
	private String meses;

    public ReporteDemunasMeses() {
    }

	public Long getCasos() {
		return this.casos;
	}

	
	public void setCasos(Long casos) {
		this.casos = casos;
	}
	
	public Integer getKydependencia() {
		return this.kydependencia;
	}
	
	public void setKydependencia(Integer kydependencia) {
		this.kydependencia = kydependencia;
	}

	public String getDemuna() {
		return this.demuna;
	}
	
	public void setDemuna(String demuna) {
		 this.demuna = demuna;
	}

	public String getMeses() {
		return this.meses;
	}
	
	public void setMeses(String meses) {
		 this.meses = meses;
	}

	@Override
	public String toString() {
		return "Casos [casos=" + casos + ", demuna=" + demuna +  ", meses=" + meses +"]";
	}
	
}