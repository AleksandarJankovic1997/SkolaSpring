package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ajpitanja database table.
 * 
 */
@Entity
@NamedQuery(name="Ajpitanja.findAll", query="SELECT a FROM Ajpitanja a")
public class Ajpitanja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPitanja;

	private int brojPoena;

	private String pitanje;

	//bi-directional many-to-one association to Ajodgovor
	@OneToMany(mappedBy="ajpitanja")
	private List<Ajodgovor> ajodgovors;

	//bi-directional many-to-one association to Ajtest
	@ManyToOne
	private Ajtest ajtest;

	public Ajpitanja() {
	}

	public int getIdPitanja() {
		return this.idPitanja;
	}

	public void setIdPitanja(int idPitanja) {
		this.idPitanja = idPitanja;
	}

	public int getBrojPoena() {
		return this.brojPoena;
	}

	public void setBrojPoena(int brojPoena) {
		this.brojPoena = brojPoena;
	}

	public String getPitanje() {
		return this.pitanje;
	}

	public void setPitanje(String pitanje) {
		this.pitanje = pitanje;
	}

	public List<Ajodgovor> getAjodgovors() {
		return this.ajodgovors;
	}

	public void setAjodgovors(List<Ajodgovor> ajodgovors) {
		this.ajodgovors = ajodgovors;
	}

	public Ajodgovor addAjodgovor(Ajodgovor ajodgovor) {
		getAjodgovors().add(ajodgovor);
		ajodgovor.setAjpitanja(this);

		return ajodgovor;
	}

	public Ajodgovor removeAjodgovor(Ajodgovor ajodgovor) {
		getAjodgovors().remove(ajodgovor);
		ajodgovor.setAjpitanja(null);

		return ajodgovor;
	}

	public Ajtest getAjtest() {
		return this.ajtest;
	}

	public void setAjtest(Ajtest ajtest) {
		this.ajtest = ajtest;
	}

}