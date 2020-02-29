package model;

import java.io.Serializable;
import javax.persistence.*;

import com.sun.istack.NotNull;

import java.util.List;


/**
 * The persistent class for the ajpredmet database table.
 * 
 */
@Entity
@NamedQuery(name="Ajpredmet.findAll", query="SELECT a FROM Ajpredmet a")
public class Ajpredmet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPredmet;

	private String naziv;

	//bi-directional many-to-one association to Ajobavestenje
	@OneToMany(mappedBy="ajpredmet")
	private List<Ajobavestenje> ajobavestenjes;

	//bi-directional many-to-one association to Ajodgovor
	@OneToMany(mappedBy="ajpredmet")
	private List<Ajodgovor> ajodgovors;

	//bi-directional many-to-one association to Ajasistent
	@ManyToOne
	@JoinColumn(name="Asistent_idAsistent")
	private Ajasistent ajasistent;

	//bi-directional many-to-one association to Ajprofesor
	@ManyToOne
	@JoinColumn(name="Profesor_idProfesor")
	private Ajprofesor ajprofesor;

	//bi-directional many-to-many association to Ajstudent
	@ManyToMany(mappedBy="ajpredmets")
	private List<Ajstudent> ajstudents;



	//bi-directional many-to-one association to Ajtest
	@OneToMany(mappedBy="ajpredmet")
	private List<Ajtest> ajtests;

	public Ajpredmet() {
	}

	public int getIdPredmet() {
		return this.idPredmet;
	}

	public void setIdPredmet(int idPredmet) {
		this.idPredmet = idPredmet;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Ajobavestenje> getAjobavestenjes() {
		return this.ajobavestenjes;
	}

	public void setAjobavestenjes(List<Ajobavestenje> ajobavestenjes) {
		this.ajobavestenjes = ajobavestenjes;
	}

	public Ajobavestenje addAjobavestenje(Ajobavestenje ajobavestenje) {
		getAjobavestenjes().add(ajobavestenje);
		ajobavestenje.setAjpredmet(this);

		return ajobavestenje;
	}

	public Ajobavestenje removeAjobavestenje(Ajobavestenje ajobavestenje) {
		getAjobavestenjes().remove(ajobavestenje);
		ajobavestenje.setAjpredmet(null);

		return ajobavestenje;
	}

	public List<Ajodgovor> getAjodgovors() {
		return this.ajodgovors;
	}

	public void setAjodgovors(List<Ajodgovor> ajodgovors) {
		this.ajodgovors = ajodgovors;
	}

	public Ajodgovor addAjodgovor(Ajodgovor ajodgovor) {
		getAjodgovors().add(ajodgovor);
		ajodgovor.setAjpredmet(this);

		return ajodgovor;
	}

	public Ajodgovor removeAjodgovor(Ajodgovor ajodgovor) {
		getAjodgovors().remove(ajodgovor);
		ajodgovor.setAjpredmet(null);

		return ajodgovor;
	}

	public Ajasistent getAjasistent() {
		return this.ajasistent;
	}

	public void setAjasistent(Ajasistent ajasistent) {
		this.ajasistent = ajasistent;
	}

	public Ajprofesor getAjprofesor() {
		return this.ajprofesor;
	}

	public void setAjprofesor(Ajprofesor ajprofesor) {
		this.ajprofesor = ajprofesor;
	}

	public List<Ajstudent> getAjstudents() {
		return this.ajstudents;
	}

	public void setAjstudents1(List<Ajstudent> ajstudents1) {
		this.ajstudents = ajstudents1;
	}

	

	public List<Ajtest> getAjtests() {
		return this.ajtests;
	}

	public void setAjtests(List<Ajtest> ajtests) {
		this.ajtests = ajtests;
	}

	public Ajtest addAjtest(Ajtest ajtest) {
		getAjtests().add(ajtest);
		ajtest.setAjpredmet(this);

		return ajtest;
	}

	public Ajtest removeAjtest(Ajtest ajtest) {
		getAjtests().remove(ajtest);
		ajtest.setAjpredmet(null);

		return ajtest;
	}

}