package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ajobavestenje database table.
 * 
 */
@Entity
@NamedQuery(name="Ajobavestenje.findAll", query="SELECT a FROM Ajobavestenje a")
public class Ajobavestenje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idObavestenje;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datum;

	private String tekst;

	//bi-directional many-to-one association to Ajpredmet
	@ManyToOne
	private Ajpredmet ajpredmet;

	public Ajobavestenje() {
	}

	public int getIdObavestenje() {
		return this.idObavestenje;
	}

	public void setIdObavestenje(int idObavestenje) {
		this.idObavestenje = idObavestenje;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Ajpredmet getAjpredmet() {
		return this.ajpredmet;
	}

	public void setAjpredmet(Ajpredmet ajpredmet) {
		this.ajpredmet = ajpredmet;
	}

}