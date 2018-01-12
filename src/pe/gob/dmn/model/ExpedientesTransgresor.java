package pe.gob.dmn.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the EXPEDIENTES_TRANSGRESORES database table.
 * 
 */
@Entity
@Table(name = "EXPEDIENTES_TRANSGRESORES")
public class ExpedientesTransgresor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "EXPEDIENTES_TRANSGRESORES_KYTRANSGRESOR_GENERATOR", sequenceName = "SEQ_EXPEDIENTES_TRANSGRESORES")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXPEDIENTES_TRANSGRESORES_KYTRANSGRESOR_GENERATOR")
	private long kytransgresor;

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

	private String relacion;

	private String sexo;

	private BigDecimal tipo;

	// bi-directional many-to-one association to Expediente
	@ManyToOne
	@JoinColumn(name = "KYEXPEDIENTE")
	private Expediente expediente;

	public ExpedientesTransgresor() {
	}

	public long getKytransgresor() {
		return this.kytransgresor;
	}

	public void setKytransgresor(long kytransgresor) {
		this.kytransgresor = kytransgresor;
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

	public String getRelacion() {
		return this.relacion;
	}

	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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