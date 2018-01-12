package pe.gob.dmn.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EXPEDIENTES_ENTREVISTAS database table.
 * 
 */
@Entity
@Table(name="EXPEDIENTES_ENTREVISTAS")
public class ExpedientesEntrevista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXPEDIENTES_ENTREVISTAS_KYENTREVISTA_GENERATOR", sequenceName="SEQ_EXPEDIENTES_ENTREVISTAS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXPEDIENTES_ENTREVISTAS_KYENTREVISTA_GENERATOR")
	private long kyentrevista;

	private String fecha;

	private String informe;

	private String nombre;
	
	private String appaterno;
	
	private String apmaterno;
	
	private String nombres;

	private String otros;

	private String tipo;

	//bi-directional many-to-one association to Expediente
    @ManyToOne
	@JoinColumn(name="KYEXPEDIENTE")
	private Expediente expediente;

    public ExpedientesEntrevista() {
    }

	public long getKyentrevista() {
		return this.kyentrevista;
	}

	public void setKyentrevista(long kyentrevista) {
		this.kyentrevista = kyentrevista;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getOtros() {
		return this.otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Expediente getExpediente() {
		return this.expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	
}