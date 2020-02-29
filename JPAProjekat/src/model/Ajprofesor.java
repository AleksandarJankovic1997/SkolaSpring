package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ajprofesor database table.
 * 
 */
@Entity
@NamedQuery(name="Ajprofesor.findAll", query="SELECT a FROM Ajprofesor a")
public class Ajprofesor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProfesor;

	private String ime;

	private String prezime;

	private String username;

	private String zvanje;

	//bi-directional many-to-one association to Ajpredmet
	@OneToMany(mappedBy="ajprofesor")
	private List<Ajpredmet> ajpredmets;

	public Ajprofesor() {
	}

	public int getIdProfesor() {
		return this.idProfesor;
	}

	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
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

	public String getZvanje() {
		return this.zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public List<Ajpredmet> getAjpredmets() {
		return this.ajpredmets;
	}

	public void setAjpredmets(List<Ajpredmet> ajpredmets) {
		this.ajpredmets = ajpredmets;
	}

	public Ajpredmet addAjpredmet(Ajpredmet ajpredmet) {
		getAjpredmets().add(ajpredmet);
		ajpredmet.setAjprofesor(this);

		return ajpredmet;
	}

	public Ajpredmet removeAjpredmet(Ajpredmet ajpredmet) {
		getAjpredmets().remove(ajpredmet);
		ajpredmet.setAjprofesor(null);

		return ajpredmet;
	}

}