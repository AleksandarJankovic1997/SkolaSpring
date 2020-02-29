package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ajtest database table.
 * 
 */
@Entity
@NamedQuery(name="Ajtest.findAll", query="SELECT a FROM Ajtest a")
public class Ajtest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSemaTest;

	private String imeTesta;

	//bi-directional many-to-one association to Ajpitanja
	@OneToMany(mappedBy="ajtest")
	private List<Ajpitanja> ajpitanjas;

	//bi-directional many-to-one association to Ajpredmet
	@ManyToOne
	private Ajpredmet ajpredmet;

	public Ajtest() {
	}

	public int getIdSemaTest() {
		return this.idSemaTest;
	}

	public void setIdSemaTest(int idSemaTest) {
		this.idSemaTest = idSemaTest;
	}

	public String getImeTesta() {
		return this.imeTesta;
	}

	public void setImeTesta(String imeTesta) {
		this.imeTesta = imeTesta;
	}

	public List<Ajpitanja> getAjpitanjas() {
		return this.ajpitanjas;
	}

	public void setAjpitanjas(List<Ajpitanja> ajpitanjas) {
		this.ajpitanjas = ajpitanjas;
	}

	public Ajpitanja addAjpitanja(Ajpitanja ajpitanja) {
		getAjpitanjas().add(ajpitanja);
		ajpitanja.setAjtest(this);

		return ajpitanja;
	}

	public Ajpitanja removeAjpitanja(Ajpitanja ajpitanja) {
		getAjpitanjas().remove(ajpitanja);
		ajpitanja.setAjtest(null);

		return ajpitanja;
	}

	public Ajpredmet getAjpredmet() {
		return this.ajpredmet;
	}

	public void setAjpredmet(Ajpredmet ajpredmet) {
		this.ajpredmet = ajpredmet;
	}

}