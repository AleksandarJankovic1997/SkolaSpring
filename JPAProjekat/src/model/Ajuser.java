package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ajuser database table.
 * 
 */
@Entity
@NamedQuery(name="Ajuser.findAll", query="SELECT a FROM Ajuser a")
public class Ajuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUser;

	private String password;

	private String uloga;

	private String username;

	//bi-directional many-to-many association to Ajrole
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="ajuser_has_role"
		, joinColumns={
			@JoinColumn(name="User_idUser")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Role_idRole")
			}
		)
	private List<Ajrole> ajroles;

	public Ajuser() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUloga() {
		return this.uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Ajrole> getAjroles() {
		return this.ajroles;
	}

	public void setAjroles(List<Ajrole> ajroles) {
		this.ajroles = ajroles;
	}

}