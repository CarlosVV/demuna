package pe.gob.dmn.model;

import java.io.Serializable;

/**
 * The persistent class for the DEMUNAS database table.
 * 
 */ 

public class ReporteDemunasMesesMaterias implements Serializable {
	private static final long serialVersionUID = 1L;


	
	private Long casos;
	
	private Integer kymateria;

	private String materia;
	
	private String meses;

    public ReporteDemunasMesesMaterias() {
    }

	public Long getCasos() {
		return this.casos;
	}
	
	public void setCasos(Long casos) {
		this.casos = casos;
	}
	
	public Integer getKymateria() {
		return this.kymateria;
	}
	
	public void setKymateria(Integer kymateria) {
		this.kymateria = kymateria;
	}

	public String getMateria() {
		return this.materia;
	}
	
	public void setMateria(String materia) {
		 this.materia = materia;
	}

	public String getMeses() {
		return this.meses;
	}
	
	public void setMeses(String meses) {
		 this.meses = meses;
	}

	@Override
	public String toString() {
		return "Casos [casos=" + casos + ", materia=" + materia +  ", meses=" + meses +"]";
	}
	
}