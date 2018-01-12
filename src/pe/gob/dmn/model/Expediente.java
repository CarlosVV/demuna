package pe.gob.dmn.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the EXPEDIENTES database table.
 * 
 */
@Entity
@Table(name="EXPEDIENTES")
public class Expediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXPEDIENTES_KYEXPEDIENTE_GENERATOR", sequenceName="SEQ_EXPEDIENTES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXPEDIENTES_KYEXPEDIENTE_GENERATOR")
	private Long kyexpediente;

	private String acciones;

	private String c1;

	private String c2;

	private String c3;

	private String c4;

	private String c5;

	private String c6;

	private String derivacion;
	
	private String derivade;

	private Integer estexp;

	private Integer estop;

	private String fecha;

	private String lugar;

	private String nroexpediente;

	private String resumenhechos;

	//bi-directional many-to-one association to Demuna
    @ManyToOne
	@JoinColumn(name="KYDEPENDENCIA")
	private Demuna demuna;

	//bi-directional many-to-one association to Materia
    @ManyToOne
	@JoinColumn(name="KYMATERIA")
	private Materia materia;

	//bi-directional many-to-one association to ExpedientesAfectado
	@OneToMany(mappedBy="expediente")
	private List<ExpedientesAfectado> expedientesAfectados;

	//bi-directional many-to-one association to ExpedientesCompromiso
	@OneToMany(mappedBy="expediente")
	private List<ExpedientesCompromiso> expedientesCompromisos;

	//bi-directional many-to-one association to ExpedientesConciliacione
	@OneToMany(mappedBy="expediente")
	private List<ExpedientesConciliacione> expedientesConciliaciones;

	//bi-directional many-to-one association to ExpedientesEntrevista
	@OneToMany(mappedBy="expediente")
	private List<ExpedientesEntrevista> expedientesEntrevistas;

	//bi-directional many-to-one association to ExpedientesInformante
	@OneToMany(mappedBy="expediente")
	private List<ExpedientesInformante> expedientesInformantes;

	//bi-directional many-to-one association to ExpedientesMateria
	@OneToMany(mappedBy="expediente")
	private List<ExpedientesMateria> expedientesMaterias;

	//bi-directional many-to-one association to ExpedientesSolicitude
	@OneToMany(mappedBy="expediente")
	private List<ExpedientesSolicitud> expedientesSolicitudes;

	//bi-directional many-to-one association to ExpedientesTransgresore
	@OneToMany(mappedBy="expediente")
	private List<ExpedientesTransgresor> expedientesTransgresores;

	//bi-directional many-to-one association to ExpedientesVerificacion
	@OneToMany(mappedBy="expediente")
	private List<ExpedientesVerificacion> expedientesVerificacions;

    public Expediente() {
    }

	public Long getKyexpediente() {
		return this.kyexpediente;
	}

	public void setKyexpediente(Long kyexpediente) {
		this.kyexpediente = kyexpediente;
	}

	public String getAcciones() {
		return this.acciones;
	}

	public void setAcciones(String acciones) {
		this.acciones = acciones;
	}

	public String getC1() {
		return this.c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return this.c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return this.c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC4() {
		return this.c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public String getC5() {
		return this.c5;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	public String getC6() {
		return this.c6;
	}

	public void setC6(String c6) {
		this.c6 = c6;
	}

	public String getDerivacion() {
		return this.derivacion;
	}

	public void setDerivacion(String derivacion) {
		this.derivacion = derivacion;
	}
	
	public String getDerivaDe() {
		return this.derivade;
	}

	public void setDerivaDe(String derivade) {
		this.derivade = derivade;
	}

	public Integer getEstexp() {
		return this.estexp;
	}

	public void setEstexp(Integer estexp) {
		this.estexp = estexp;
	}

	public Integer getEstop() {
		return this.estop;
	}

	public void setEstop(Integer estop) {
		this.estop = estop;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getLugar() {
		return this.lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getNroexpediente() {
		return this.nroexpediente;
	}

	public void setNroexpediente(String nroexpediente) {
		this.nroexpediente = nroexpediente;
	}

	public String getResumenhechos() {
		return this.resumenhechos;
	}

	public void setResumenhechos(String resumenhechos) {
		this.resumenhechos = resumenhechos;
	}

	public Demuna getDemuna() {
		return this.demuna;
	}

	public void setDemuna(Demuna demuna) {
		this.demuna = demuna;
	}
	
	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	public List<ExpedientesAfectado> getExpedientesAfectados() {
		return this.expedientesAfectados;
	}

	public void setExpedientesAfectados(List<ExpedientesAfectado> expedientesAfectados) {
		this.expedientesAfectados = expedientesAfectados;
	}
	
	public List<ExpedientesCompromiso> getExpedientesCompromisos() {
		return this.expedientesCompromisos;
	}

	public void setExpedientesCompromisos(List<ExpedientesCompromiso> expedientesCompromisos) {
		this.expedientesCompromisos = expedientesCompromisos;
	}
	
	public List<ExpedientesConciliacione> getExpedientesConciliaciones() {
		return this.expedientesConciliaciones;
	}

	public void setExpedientesConciliaciones(List<ExpedientesConciliacione> expedientesConciliaciones) {
		this.expedientesConciliaciones = expedientesConciliaciones;
	}
	
	public List<ExpedientesEntrevista> getExpedientesEntrevistas() {
		return this.expedientesEntrevistas;
	}

	public void setExpedientesEntrevistas(List<ExpedientesEntrevista> expedientesEntrevistas) {
		this.expedientesEntrevistas = expedientesEntrevistas;
	}
	
	public List<ExpedientesInformante> getExpedientesInformantes() {
		return this.expedientesInformantes;
	}

	public void setExpedientesInformantes(List<ExpedientesInformante> expedientesInformantes) {
		this.expedientesInformantes = expedientesInformantes;
	}
	
	public List<ExpedientesMateria> getExpedientesMaterias() {
		return this.expedientesMaterias;
	}

	public void setExpedientesMaterias(List<ExpedientesMateria> expedientesMaterias) {
		this.expedientesMaterias = expedientesMaterias;
	}
	
	public List<ExpedientesSolicitud> getExpedientesSolicitudes() {
		return this.expedientesSolicitudes;
	}

	public void setExpedientesSolicitudes(List<ExpedientesSolicitud> expedientesSolicitudes) {
		this.expedientesSolicitudes = expedientesSolicitudes;
	}
	
	public List<ExpedientesTransgresor> getExpedientesTransgresores() {
		return this.expedientesTransgresores;
	}

	public void setExpedientesTransgresores(List<ExpedientesTransgresor> expedientesTransgresores) {
		this.expedientesTransgresores = expedientesTransgresores;
	}
	
	public List<ExpedientesVerificacion> getExpedientesVerificacions() {
		return this.expedientesVerificacions;
	}

	public void setExpedientesVerificacions(List<ExpedientesVerificacion> expedientesVerificacions) {
		this.expedientesVerificacions = expedientesVerificacions;
	}
	
}