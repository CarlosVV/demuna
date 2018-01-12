package pe.gob.dmn.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EXPEDIENTES_VERIFICACION database table.
 * 
 */
@Entity
@Table(name="EXPEDIENTES_VERIFICACION")
public class ExpedientesVerificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXPEDIENTES_VERIFICACION_KYVERIFICACION_GENERATOR", sequenceName="SEQ_EXPEDIENTES_VERIFICACION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXPEDIENTES_VERIFICACION_KYVERIFICACION_GENERATOR")
	private long kyverificacion;

	private String autorizacion;

	private String concluciones;

	private String defensor;
	
	private String nombres;
	
	private String appaterno;
	
	private String apmaterno;

	private String docidentidad;

	private String fecha;

	private String horafin;

	private String horainicio;

	private String motverificacion;

	private String observacion;

	//bi-directional many-to-one association to Expediente
    @ManyToOne
	@JoinColumn(name="KYEXPEDIENTE")
	private Expediente expediente;

    public ExpedientesVerificacion() {
    }

	public long getKyverificacion() {
		return this.kyverificacion;
	}

	public void setKyverificacion(long kyverificacion) {
		this.kyverificacion = kyverificacion;
	}

	public String getAutorizacion() {
		return this.autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public String getConcluciones() {
		return this.concluciones;
	}

	public void setConcluciones(String concluciones) {
		this.concluciones = concluciones;
	}

	public String getDefensor() {
		return this.defensor;
	}

	public void setDefensor(String defensor) {
		this.defensor = defensor;
	}
	
	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public String getAppaterno() {
		return this.appaterno;
	}

	public void setAppaterno(String appaterno) {
		this.appaterno = appaterno;
	}
	
	
	public String getApmaterno() {
		return this.apmaterno;
	}

	public void setApmaterno(String apmaterno) {
		this.apmaterno = apmaterno;
	}

	public String getDocidentidad() {
		return this.docidentidad;
	}

	public void setDocidentidad(String docidentidad) {
		this.docidentidad = docidentidad;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHorafin() {
		return this.horafin;
	}

	public void setHorafin(String horafin) {
		this.horafin = horafin;
	}

	public String getHorainicio() {
		return this.horainicio;
	}

	public void setHorainicio(String horainicio) {
		this.horainicio = horainicio;
	}

	public String getMotverificacion() {
		return this.motverificacion;
	}

	public void setMotverificacion(String motverificacion) {
		this.motverificacion = motverificacion;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Expediente getExpediente() {
		return this.expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	
}