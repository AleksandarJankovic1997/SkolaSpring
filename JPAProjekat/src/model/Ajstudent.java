package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ajstudent database table.
 * 
 */
@Entity
@NamedQuery(name="Ajstudent.findAll", query="SELECT a FROM Ajstudent a")
public class Ajstudent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idStudent;

	private int brojIndeksa;

	@Temporal(TemporalType.DATE)
	private Date datumR;

	private String ime;
	
	

	private String prezime;

	private String username;

	private String smer;
	
	private String adresa;
	//bi-directional many-to-one association to Ajodgovor
	@OneToMany(mappedBy="ajstudent")
	private List<Ajodgovor> ajodgovors;

	//bi-directional many-to-many association to Ajpredmet
	@ManyToMany
	@JoinTable(
		name="ajstudent_has_predmet"
		, joinColumns={
			@JoinColumn(name="Student_idStudent")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Predmet_idPredmet")
			}
		)
	private List<Ajpredmet> ajpredmets;

	public Ajstudent() {
	}

	public int getIdStudent() {
		return this.idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	public int getBrojIndeksa() {
		return this.brojIndeksa;
	}

	public void setBrojIndeksa(int brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}

	public Date getDatumR() {
		return this.datumR;
	}

	public void setDatumR(Date datumR) {
		this.datumR = datumR;
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
	public String getSmer() {
		return this.smer;
	}
	public void setSmer(String smer) {
		this.smer=smer;
	}
	public String getAdresa() {
		return this.adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa=adresa;
	}
	public List<Ajodgovor> getAjodgovors() {
		return this.ajodgovors;
	}

	public void setAjodgovors(List<Ajodgovor> ajodgovors) {
		this.ajodgovors = ajodgovors;
	}

	public Ajodgovor addAjodgovor(Ajodgovor ajodgovor) {
		getAjodgovors().add(ajodgovor);
		ajodgovor.setAjstudent(this);

		return ajodgovor;
	}

	public Ajodgovor removeAjodgovor(Ajodgovor ajodgovor) {
		getAjodgovors().remove(ajodgovor);
		ajodgovor.setAjstudent(null);

		return ajodgovor;
	}

	public List<Ajpredmet> getAjpredmets() {
		return this.ajpredmets;
	}

	public void setAjpredmets1(List<Ajpredmet> ajpredmets1) {
		this.ajpredmets = ajpredmets1;
	}


}