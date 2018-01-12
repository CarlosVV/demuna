package pe.gob.dmn.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name="USUARIOS")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIOS_KYUSUARIO_GENERATOR", sequenceName="SEQ_USUARIOS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIOS_KYUSUARIO_GENERATOR")
	private long kyusuario;

	private String clave;

	private String email;

	private Integer estado;

	private Integer nivel;

	private String nombres;
	
	private String appaterno;
	
	private String apmaterno;
	
	private String nombre;
	
	private String telefonos;

	private String usuario;

	//bi-directional many-to-one association to Demuna
    @ManyToOne
	@JoinColumn(name="KYDEMUNA")
	private Demuna demuna;

    public Usuario() {
    }

	public long getKyusuario() {
		return this.kyusuario;
	}

	public void setKyusuario(long kyusuario) {
		this.kyusuario = kyusuario;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getNivel() {
		return this.nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
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
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefonos() {
		return this.telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Demuna getDemuna() {
		return this.demuna;
	}

	public void setDemuna(Demuna demuna) {
		this.demuna = demuna;
	}

	@Override
	public String toString() {
		return "Usuario [kyusuario=" + kyusuario + ", clave=" + clave
				+ ", email=" + email + ", estado=" + estado + ", nivel="
				+ nivel + ", nombres=" + nombres + ", telefonos=" + telefonos
				+ ", usuario=" + usuario + ", demuna=" + demuna + "]";
	}
	
}