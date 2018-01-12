package pe.gob.dmn.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the LOCATION database table.
 * 
 */
@Entity
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOCATION_KYLOCATION_GENERATOR", sequenceName="SEQ_LOCATION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCATION_KYLOCATION_GENERATOR")
	private long kylocation;

	private String coddist;

	private String coddpto;

	private String codprov;

	private String name;

    public Location() {
    }

	public long getKylocation() {
		return this.kylocation;
	}

	public void setKylocation(long kylocation) {
		this.kylocation = kylocation;
	}

	public String getCoddist() {
		return this.coddist;
	}

	public void setCoddist(String coddist) {
		this.coddist = coddist;
	}

	public String getCoddpto() {
		return this.coddpto;
	}

	public void setCoddpto(String coddpto) {
		this.coddpto = coddpto;
	}

	public String getCodprov() {
		return this.codprov;
	}

	public void setCodprov(String codprov) {
		this.codprov = codprov;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}