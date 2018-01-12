package pe.gob.dmn.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the EXPEDIENTES_INFORMANTES database table.
 * 
 */
@Entity
@Table(name="EXPEDIENTES_INFORMANTES")
public class ExpedientesInformante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXPEDIENTES_INFORMANTES_KYINFORMANTE_GENERATOR", sequenceName="SEQ_EXPEDIENTES_INFORMANTES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXPEDIENTES_INFORMANTES_KYINFORMANTE_GENERATOR")
	private long kyinformante;

	private String dni;

	private String domicilio;

	private Integer edad;

	private Integer estado;

	private String estcivil;

	private String kydepartamento;

	private String nombre;
	
	private String appaterno;
	
	private String apmaterno;
	
	private String nombres;

	private String ocupacion;

	@Column(name="RELACION_AFECTADO")
	private String relacionAfectado;

	private String sexo;

	private String telefono;

	private BigDecimal tipo;

	//bi-directional many-to-one association to Expediente
    @ManyToOne
	@JoinColumn(name="KYEXPEDIENTE")
	private Expediente expediente;

    public ExpedientesInformante() {
    }

	public long getKyinformante() {
		return this.kyinformante;
	}

	public void setKyinformante(long kyinformante) {
		this.kyinformante = kyinformante;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getEstcivil() {
		return this.estcivil;
	}

	public void setEstcivil(String estcivil) {
		this.estcivil = estcivil;
	}

	public String getKydepartamento() {
		return this.kydepartamento;
	}

	public void setKydepartamento(String kydepartamento) {
		this.kydepartamento = kydepartamento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApPaterno() {
		return this.appaterno;
	}

	public void setApPaterno(String appaterno) {
		this.appaterno = appaterno;
	}
	
	public String getApMaterno() {
		return this.apmaterno;
	}

	public void setApMaterno(String apmaterno) {
		this.apmaterno = apmaterno;
	}
	
	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getOcupacion() {
		return this.ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getRelacionAfectado() {
		return this.relacionAfectado;
	}

	public void setRelacionAfectado(String relacionAfectado) {
		this.relacionAfectado = relacionAfectado;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public BigDecimal getTipo() {
		return this.tipo;
	}

	public void setTipo(BigDecimal tipo) {
		this.tipo = tipo;
	}

	public Expediente getExpediente() {
		return this.expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	
}