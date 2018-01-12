package pe.gob.dmn.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the DEMUNAS database table.
 * 
 */ 
@Entity
@Table(name="DEMUNAS")
public class Demuna implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEMUNAS_KYDEMUNA_GENERATOR", sequenceName="SEQ_DEMUNAS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEMUNAS_KYDEMUNA_GENERATOR")
	private Integer kydemuna;

	private String cecte;

	private String coordinador;

	private String correo;

	private Integer dep1;

	private Integer dep2;

	@Lob()
	private byte[] archivo;
	             
	private Integer dep3;

	private Integer dep4;

	private Integer dep5;

	private String direccion;

	private Integer estado;

	private String fechacreacion;

	private String imagen;

	private String mimdesnro;

	private String nombre;

	private String nrocecte;

	private String referencia;

	private String resolucionnro;

	private String telefono;

	private String ubicacion;

	//bi-directional many-to-one association to DemunaNivel
    @ManyToOne
	@JoinColumn(name="KYTIPODEMUNA")
	private DemunaNivel demunaNivel;

	//bi-directional many-to-one association to Expediente
	@OneToMany(mappedBy="demuna")
	private List<Expediente> expedientes;

	//bi-directional many-to-one association to ExpedientesMateria
	@OneToMany(mappedBy="demuna")
	private List<ExpedientesMateria> expedientesMaterias;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="demuna")
	private List<Usuario> usuarios;

    public Demuna() {
    }

	public Integer getKydemuna() {
		return this.kydemuna;
	}

	public void setKydemuna(Integer kydemuna) {
		this.kydemuna = kydemuna;
	}

	public String getCecte() {
		return this.cecte;
	}

	public void setCecte(String cecte) {
		this.cecte = cecte;
	}

	public String getCoordinador() {
		return this.coordinador;
	}

	public void setCoordinador(String coordinador) {
		this.coordinador = coordinador;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getDep1() {
		return this.dep1;
	}

	public void setDep1(Integer dep1) {
		this.dep1 = dep1;
	}

	public Integer getDep2() {
		return this.dep2;
	}

	public void setDep2(Integer dep2) {
		this.dep2 = dep2;
	}

	public Integer getDep3() {
		return this.dep3;
	}

	public void setDep3(Integer dep3) {
		this.dep3 = dep3;
	}

	public Integer getDep4() {
		return this.dep4;
	}

	public void setDep4(Integer dep4) {
		this.dep4 = dep4;
	}

	public Integer getDep5() {
		return this.dep5;
	}

	public void setDep5(Integer dep5) {
		this.dep5 = dep5;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(String fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getMimdesnro() {
		return this.mimdesnro;
	}

	public void setMimdesnro(String mimdesnro) {
		this.mimdesnro = mimdesnro;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNrocecte() {
		return this.nrocecte;
	}

	public void setNrocecte(String nrocecte) {
		this.nrocecte = nrocecte;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getResolucionnro() {
		return this.resolucionnro;
	}

	public void setResolucionnro(String resolucionnro) {
		this.resolucionnro = resolucionnro;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public DemunaNivel getDemunaNivel() {
		return this.demunaNivel;
	}

	public void setDemunaNivel(DemunaNivel demunaNivel) {
		this.demunaNivel = demunaNivel;
	}
	
	public List<Expediente> getExpedientes() {
		return this.expedientes;
	}

	public void setExpedientes(List<Expediente> expedientes) {
		this.expedientes = expedientes;
	}
	
	public List<ExpedientesMateria> getExpedientesMaterias() {
		return this.expedientesMaterias;
	}

	public void setExpedientesMaterias(List<ExpedientesMateria> expedientesMaterias) {
		this.expedientesMaterias = expedientesMaterias;
	}
	
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return "Demuna [kydemuna=" + kydemuna + ", direccion=" + direccion
				+ ", estado=" + estado + "]";
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}
	
}