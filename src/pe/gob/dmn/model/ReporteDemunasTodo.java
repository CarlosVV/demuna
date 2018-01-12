package pe.gob.dmn.model;

import java.io.Serializable;

/**
 * The persistent class for the DEMUNAS database table.
 * 
 */ 

public class ReporteDemunasTodo implements Serializable {
	private static final long serialVersionUID = 1L;


	
	private Long casos;
	
	private Integer kydependencia;

	private String demuna;

    public ReporteDemunasTodo() {
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


	@Override
	public String toString() {
		return "Casos [casos=" + casos + ", demuna=" + demuna +  "]";
	}
	
}