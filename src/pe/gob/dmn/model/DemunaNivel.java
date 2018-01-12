package pe.gob.dmn.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the DEMUNA_NIVEL database table.
 * 
 */
@Entity
@Table(name="DEMUNA_NIVEL")
public class DemunaNivel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEMUNA_NIVEL_KYTIPODEMUNA_GENERATOR", sequenceName="SEQ_DEMUNA_NIVEL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEMUNA_NIVEL_KYTIPODEMUNA_GENERATOR")
	private Integer kytipodemuna;

	private String descripcion;

	private Integer estado;

	//bi-directional many-to-one association to Demuna
	@OneToMany(mappedBy="demunaNivel")
	private List<Demuna> demunas;

    public DemunaNivel() {
    }

	public Integer getKytipodemuna() {
		return this.kytipodemuna;
	}

	public void setKytipodemuna(Integer kytipodemuna) {
		this.kytipodemuna = kytipodemuna;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public List<Demuna> getDemunas() {
		return this.demunas;
	}

	public void setDemunas(List<Demuna> demunas) {
		this.demunas = demunas;
	}
	
}