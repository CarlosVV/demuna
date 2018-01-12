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
 * The persistent class for the MATERIAS database table.
 * 
 */
@Entity
@Table(name="MATERIAS")
public class Materia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MATERIAS_KYMATERIA_GENERATOR", sequenceName="SEQ_MATERIAS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MATERIAS_KYMATERIA_GENERATOR")
	private Integer kymateria;

	private String conciliable;

	private Integer estmat;

	private String materia;

	//bi-directional many-to-one association to Expediente
	@OneToMany(mappedBy="materia")
	private List<Expediente> expedientes;

	//bi-directional many-to-one association to ExpedientesMateria
	@OneToMany(mappedBy="materia")
	private List<ExpedientesMateria> expedientesMaterias;

    public Materia() {
    }

	public Integer getKymateria() {
		return this.kymateria;
	}

	public void setKymateria(Integer kymateria) {
		this.kymateria = kymateria;
	}

	public String getConciliable() {
		return this.conciliable;
	}

	public void setConciliable(String conciliable) {
		this.conciliable = conciliable;
	}

	public Integer getEstmat() {
		return this.estmat;
	}

	public void setEstmat(Integer estmat) {
		this.estmat = estmat;
	}

	public String getMateria() {
		return this.materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
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
	
}