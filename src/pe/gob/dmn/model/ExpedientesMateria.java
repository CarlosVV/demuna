package pe.gob.dmn.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EXPEDIENTES_MATERIAS database table.
 * 
 */
@Entity
@Table(name="EXPEDIENTES_MATERIAS")
public class ExpedientesMateria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXPEDIENTES_MATERIAS_KYEXPEDIENTEMATERIA_GENERATOR", sequenceName="SEQ_EXPEDIENTES_MATERIAS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXPEDIENTES_MATERIAS_KYEXPEDIENTEMATERIA_GENERATOR")
	private long kyexpedientemateria;

	private String fecha;

	//bi-directional many-to-one association to Demuna
    @ManyToOne
	@JoinColumn(name="KYDEPENDENCIA")
	private Demuna demuna;

	//bi-directional many-to-one association to Expediente
    @ManyToOne
	@JoinColumn(name="KYEXPEDIENTE")
	private Expediente expediente;

	//bi-directional many-to-one association to Materia
    @ManyToOne
	@JoinColumn(name="KYMATERIA")
	private Materia materia;

    public ExpedientesMateria() {
    }

	public long getKyexpedientemateria() {
		return this.kyexpedientemateria;
	}

	public void setKyexpedientemateria(long kyexpedientemateria) {
		this.kyexpedientemateria = kyexpedientemateria;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Demuna getDemuna() {
		return this.demuna;
	}

	public void setDemuna(Demuna demuna) {
		this.demuna = demuna;
	}
	
	public Expediente getExpediente() {
		return this.expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	
	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
}