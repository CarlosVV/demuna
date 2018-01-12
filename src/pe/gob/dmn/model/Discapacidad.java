package pe.gob.dmn.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the DISCAPACIDAD database table.
 * 
 */
@Entity
@Table(name="DISCAPACIDAD")
public class Discapacidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DISCAPACIDAD_KYDISCAPACIDAD_GENERATOR", sequenceName="SEQ_DISCAPACIDAD")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DISCAPACIDAD_KYDISCAPACIDAD_GENERATOR")
	private long kydiscapacidad;

	private String discapacidad;

	private Integer estado;

    public Discapacidad() {
    }

	public long getKydiscapacidad() {
		return this.kydiscapacidad;
	}

	public void setKydiscapacidad(long kydiscapacidad) {
		this.kydiscapacidad = kydiscapacidad;
	}

	public String getDiscapacidad() {
		return this.discapacidad;
	}

	public void setDiscapacidad(String discapacidad) {
		this.discapacidad = discapacidad;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}