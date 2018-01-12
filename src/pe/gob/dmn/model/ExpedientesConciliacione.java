package pe.gob.dmn.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the EXPEDIENTES_CONCILIACIONES database table.
 * 
 */
@Entity
@Table(name="EXPEDIENTES_CONCILIACIONES")
public class ExpedientesConciliacione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXPEDIENTES_CONCILIACIONES_KYACTA_GENERATOR", sequenceName="SEQ_EXPEDIENTES_CONCILIACIONES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXPEDIENTES_CONCILIACIONES_KYACTA_GENERATOR")
	private long kyacta;

	private String acuerdos;

	private BigDecimal conciliacion;

	private String fecha;

	private String p1;

	private String p2;

	private String p3;

	private String p4;

	private String p5;

	private String seguimiento;

	//bi-directional many-to-one association to Expediente
    @ManyToOne
	@JoinColumn(name="KYEXPEDIENTE")
	private Expediente expediente;

    public ExpedientesConciliacione() {
    }

	public long getKyacta() {
		return this.kyacta;
	}

	public void setKyacta(long kyacta) {
		this.kyacta = kyacta;
	}

	public String getAcuerdos() {
		return this.acuerdos;
	}

	public void setAcuerdos(String acuerdos) {
		this.acuerdos = acuerdos;
	}

	public BigDecimal getConciliacion() {
		return this.conciliacion;
	}

	public void setConciliacion(BigDecimal conciliacion) {
		this.conciliacion = conciliacion;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getP1() {
		return this.p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return this.p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getP3() {
		return this.p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

	public String getP4() {
		return this.p4;
	}

	public void setP4(String p4) {
		this.p4 = p4;
	}

	public String getP5() {
		return this.p5;
	}

	public void setP5(String p5) {
		this.p5 = p5;
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