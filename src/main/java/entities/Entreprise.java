package entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Il s'agit de la class des entreprises
 * @author brice
 */

@Entity
@Table(name="entreprise")
public class Entreprise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nom;
	@OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL)
	private Set<Filiale> filiales;
	
	// Guetters & Setters
	
	public long getId() {
		return id;
	}
	public Set<Filiale> getFiliales() {
		return filiales;
	}
	public void setFiliales(Set<Filiale> filiales) {
		this.filiales = filiales;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
}
