package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Il s'agit de la class des secteurs
 * @author brice
 */

@Entity
@Table(name="secteur")
public class Secteur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long secteur_id;
	private String localisation;
	@OneToMany(mappedBy = "secteur", cascade = CascadeType.ALL)
	private Set<Staff> staffs;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name ="secteur_has_filiale",
			joinColumns = @JoinColumn(name ="secteur_id"),
			inverseJoinColumns = @JoinColumn(name ="filiale_id")
			)
	private Set<Filiale> filiales = new HashSet<Filiale>();
	
	/**
	 * Pour ajouter des filiales à mon secteur
	 * @param filiale
	 */
	public void addFiliale(Filiale filiale) {
		this.filiales.add(filiale);
	}
	
	// Guetters & Setters
	
	public long getSecteur_id() {
		return secteur_id;
	}
	public void setSecteur_id(long secteur_id) {
		this.secteur_id = secteur_id;
	}
	public Set<Filiale> getFiliales() {
		return filiales;
	}
	public void setFiliales(Set<Filiale> filiales) {
		this.filiales = filiales;
	}
	public Set<Staff> getStaffs() {
		return staffs;
	}
	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	
}