package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ajasistent database table.
 * 
 */
@Entity
@NamedQuery(name="Ajasistent.findAll", query="SELECT a FROM Ajasistent a")
public class Ajasistent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAsistent;

	private String ime;

	private String prezime;

	private String username;

	//bi-directional many-to-one association to Ajpredmet
	@OneToMany(mappedBy="ajasistent")
	private List<Ajpredmet> ajpredmets;

	public Ajasistent() {
	}

	public int getIdAsistent() {
		return this.idAsistent;
	}

	public void setIdAsistent(int idAsistent) {
		this.idAsistent = idAsistent;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Ajpredmet> getAjpredmets() {
		return this.ajpredmets;
	}

	public void setAjpredmets(List<Ajpredmet> ajpredmets) {
		this.ajpredmets = ajpredmets;
	}

	public Ajpredmet addAjpredmet(Ajpredmet ajpredmet) {
		getAjpredmets().add(ajpredmet);
		ajpredmet.setAjasistent(this);

		return ajpredmet;
	}

	public Ajpredmet removeAjpredmet(Ajpredmet ajpredmet) {
		getAjpredmets().remove(ajpredmet);
		ajpredmet.setAjasistent(null);

		return ajpredmet;
	}

}