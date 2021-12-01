package entities;

import javax.persistence.*;

/**
 * Il s'agit de la class des employés de l'entreprise
 * avec son constructeur vide et ses attributs
 * @author brice
 *
 */

@Entity
@Table(name = "staff")
public class Staff {
	
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private int age;
	private String fonction;
	private String telephone;
	private String adresse;
	
	
	// Constructeur vide
	public Staff() {}
	
	// Guetter & Setters
	
	@Id
	@Column(name = "id")
	//donne une stratégie de la génération de clé primaire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
}


