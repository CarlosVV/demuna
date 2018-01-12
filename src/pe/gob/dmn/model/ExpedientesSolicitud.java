package pe.gob.dmn.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EXPEDIENTES_SOLICITUDES database table.
 * 
 */
@Entity
@Table(name="EXPEDIENTES_SOLICITUDES")
public class ExpedientesSolicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXPEDIENTES_SOLICITUDES_KYSOLICITUD_GENERATOR", sequenceName="SEQ_EXPEDIENTES_SOLICITUDES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXPEDIENTES_SOLICITUDES_KYSOLICITUD_GENERATOR")
	private long kysolicitud;

	private String cargo;

	private String derivaa;

	private String fecha;

	private String informe;

	private String institucion;

	private String otros;

	private String senor;
	
	private String appaterno;
	
	private String apmaterno;
	
	private String nombres;

	//bi-directional many-to-one association to Expediente
    @ManyToOne
	@JoinColumn(name="KYEXPEDIENTE")
	private Expediente expediente;

	//bi-directional many-to-one association to ExpedientesAfectado
    @ManyToOne
	@JoinColumn(name="KYAFECTADO")
	private ExpedientesAfectado expedientesAfectado;

	//bi-directional many-to-one association to Tipoatencion
    @ManyToOne
	@JoinColumn(name="KYTIPOATENCION")
	private Tipoatencion tipoatencion;

    public ExpedientesSolicitud() {
    }

	public long getKysolicitud() {
		return this.kysolicitud;
	}

	public void setKysolicitud(long kysolicitud) {
		this.kysolicitud = kysolicitud;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDerivaa() {
		return this.derivaa;
	}

	public void setDerivaa(String derivaa) {
		this.derivaa = derivaa;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getInforme() {
		return this.informe;
	}

	public void setInforme(String informe) {
		this.informe = informe;
	}

	public String getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getOtros() {
		return this.otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

	public String getSenor() {
		return this.senor;
	}

	public void setSenor(String senor) {
		this.senor = senor;
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
	
	public String getNombres() {
		return this.nombres;
	}
	
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public Expediente getExpediente() {
		return this.expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	
	public ExpedientesAfectado getExpedientesAfectado() {
		return this.expedientesAfectado;
	}

	public void setExpedientesAfectado(ExpedientesAfectado expedientesAfectado) {
		this.expedientesAfectado = expedientesAfectado;
	}
	
	public Tipoatencion getTipoatencion() {
		return this.tipoatencion;
	}

	public void setTipoatencion(Tipoatencion tipoatencion) {
		this.tipoatencion = tipoatencion;
	}
	
}