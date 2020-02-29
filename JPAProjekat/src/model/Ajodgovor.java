package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ajodgovor database table.
 * 
 */
@Entity
@NamedQuery(name="Ajodgovor.findAll", query="SELECT a FROM Ajodgovor a")
public class Ajodgovor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOdgovor;

	private String odgovor;

	private int tacnost;

	//bi-directional many-to-one association to Ajpitanja
	@ManyToOne
	private Ajpitanja ajpitanja;

	//bi-directional many-to-one association to Ajpredmet
	@ManyToOne
	private Ajpredmet ajpredmet;

	//bi-directional many-to-one association to Ajstudent
	@ManyToOne
	private Ajstudent ajstudent;

	public Ajodgovor() {
	}

	public int getIdOdgovor() {
		return this.idOdgovor;
	}

	public void setIdOdgovor(int idOdgovor) {
		this.idOdgovor = idOdgovor;
	}

	public String getOdgovor() {
		return this.odgovor;
	}

	public void setOdgovor(String odgovor) {
		this.odgovor = odgovor;
	}

	public int getTacnost() {
		return this.tacnost;
	}

	public void setTacnost(int tacnost) {
		this.tacnost = tacnost;
	}

	public Ajpitanja getAjpitanja() {
		return this.ajpitanja;
	}

	public void setAjpitanja(Ajpitanja ajpitanja) {
		this.ajpitanja = ajpitanja;
	}

	public Ajpredmet getAjpredmet() {
		return this.ajpredmet;
	}

	public void setAjpredmet(Ajpredmet ajpredmet) {
		this.ajpredmet = ajpredmet;
	}

	public Ajstudent getAjstudent() {
		return this.ajstudent;
	}

	public void setAjstudent(Ajstudent ajstudent) {
		this.ajstudent = ajstudent;
	}

}