package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ajrole database table.
 * 
 */
@Entity
@NamedQuery(name="Ajrole.findAll", query="SELECT a FROM Ajrole a")
public class Ajrole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRole;

	private String naziv;

	//bi-directional many-to-many association to Ajuser
	
	@ManyToMany(mappedBy="ajroles",fetch=FetchType.EAGER)
	private List<Ajuser> ajusers;

	public Ajrole() {
	}

	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Ajuser> getAjusers() {
		return this.ajusers;
	}

	public void setAjusers(List<Ajuser> ajusers) {
		this.ajusers = ajusers;
	}

}