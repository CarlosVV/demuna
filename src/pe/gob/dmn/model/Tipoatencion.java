package pe.gob.dmn.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the TIPOATENCION database table.
 * 
 */
@Entity
public class Tipoatencion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPOATENCION_KYTIPOATENCION_GENERATOR", sequenceName="SEQ_TIPOATENCION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPOATENCION_KYTIPOATENCION_GENERATOR")
	private long kytipoatencion;

	private String atencion;

	private BigDecimal estado;

	//bi-directional many-to-one association to ExpedientesSolicitude
	@OneToMany(mappedBy="tipoatencion")
	private List<ExpedientesSolicitud> expedientesSolicitudes;

    public Tipoatencion() {
    }

	public long getKytipoatencion() {
		return this.kytipoatencion;
	}

	public void setKytipoatencion(long kytipoatencion) {
		this.kytipoatencion = kytipoatencion;
	}

	public String getAtencion() {
		return this.atencion;
	}

	public void setAtencion(String atencion) {
		this.atencion = atencion;
	}

	public BigDecimal getEstado() {
		return this.estado;
	}

	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}

	public List<ExpedientesSolicitud> getExpedientesSolicitudes() {
		return this.expedientesSolicitudes;
	}

	public void setExpedientesSolicitudes(List<ExpedientesSolicitud> expedientesSolicitudes) {
		this.expedientesSolicitudes = expedientesSolicitudes;
	}
	
}