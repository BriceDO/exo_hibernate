package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Il s'agit de la class des filiales
 * @author brice
 */

@Entity
@Table(name="filiale")
public class Filiale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nom;
	private int nb_employee;
	@ManyToOne
	@JoinColumn(name="entreprise_id")
	private Entreprise entreprise;
	@ManyToMany(mappedBy="filiales")
	private Set<Secteur> secteurs = new HashSet<Secteur>();
	
	/**
	 * Pour ajouter des secteurs à ma filiale
	 * @param secteur
	 */
	public void addSecteur(Secteur secteur) {
		this.secteurs.add(secteur);
	}
	
	// Guetters & Setters
	
	public Set<Secteur> getSecteurs() {
		return secteurs;
	}
	public void setSecteurs(Set<Secteur> secteurs) {
		this.secteurs = secteurs;
	}
	public long getId() {
		return id;
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
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
	public int getNb_employee() {
		return nb_employee;
	}
	public void setNb_employee(int nb_employee) {
		this.nb_employee = nb_employee;
	}
	
}
