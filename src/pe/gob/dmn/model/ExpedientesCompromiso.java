package pe.gob.dmn.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EXPEDIENTES_COMPROMISO database table.
 * 
 */
@Entity
@Table(name="EXPEDIENTES_COMPROMISO")
public class ExpedientesCompromiso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXPEDIENTES_COMPROMISO_KYCOMPROMISO_GENERATOR", sequenceName="SEQ_EXPEDIENTES_COMPROMISO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXPEDIENTES_COMPROMISO_KYCOMPROMISO_GENERATOR")
	private long kycompromiso;

	private String compromiso;

	private String descripcioncaso;

	private String fecha;

	private String seguimiento;

	//bi-directional many-to-one association to Expediente
    @ManyToOne
	@JoinColumn(name="KYEXPEDIENTE")
	private Expediente expediente;

    public ExpedientesCompromiso() {
    }

	public long getKycompromiso() {
		return this.kycompromiso;
	}

	public void setKycompromiso(long kycompromiso) {
		this.kycompromiso = kycompromiso;
	}

	public String getCompromiso() {
		return this.compromiso;
	}

	public void setCompromiso(String compromiso) {
		this.compromiso = compromiso;
	}

	public String getDescripcioncaso() {
		return this.descripcioncaso;
	}

	public void setDescripcioncaso(String descripcioncaso) {
		this.descripcioncaso = descripcioncaso;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getSeguimiento() {
		return this.seguimiento;
	}

	public void setSeguimiento(String seguimiento) {
		this.seguimiento = seguimiento;
	}

	public Expediente getExpediente() {
		return this.expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	
}