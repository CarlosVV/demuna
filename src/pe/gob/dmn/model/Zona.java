package pe.gob.dmn.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ZONAS database table.
 * 
 */
@Entity
@Table(name="ZONAS")
public class Zona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ZONAS_KYZONA_GENERATOR", sequenceName="SEQ_ZONAS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ZONAS_KYZONA_GENERATOR")
	private long kyzona;

	private String codzona;

	private String ubigeo;

	private String zona;

    public Zona() {
    }

	public long getKyzona() {
		return this.kyzona;
	}

	public void setKyzona(long kyzona) {
		this.kyzona = kyzona;
	}

	public String getCodzona() {
		return this.codzona;
	}

	public void setCodzona(String codzona) {
		this.codzona = codzona;
	}

	public String getUbigeo() {
		return this.ubigeo;
	}

	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

}