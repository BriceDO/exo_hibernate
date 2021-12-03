package manager;
import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entities.Staff;

/**
 * Va gérer les requêtes à la base de données
 * grâce à hibernate
 * @author brice
 *
 */
public class StaffManager {
	
	protected SessionFactory sessionFactory;
	
	/**
	 * permet de charger une session Hibernate
	 */
	protected void setup() {
		// Initialisation du registry
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		
		// On va essayer de se connecter à la BDD avec un try
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		// Le catch (donc si co impossible) va detruire le registre initialisé
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
			e.getStackTrace();
		}
	}
	
	/*
	 * Permet de supprimer une session Hibernate
	 */
	protected void exit() {
		System.out.println(sessionFactory);
		sessionFactory.close();
	}
	
	/**
	 * Permet de créer des employés dans ma BDD
	 */
	protected void create() {
	
		Staff staff1 = new Staff(); // Création d'un employé
		staff1.setNom("Jean");
		staff1.setPrenom("Valjean");
		staff1.setEmail("jean@jean.fr");
		staff1.setAge(45);
		staff1.setFonction("CEO");
		staff1.setTelephone("0607080910");
		staff1.setAdresse("10 rue qqpart");
		
		Staff staff2 = new Staff(); // Création d'un employé
		staff2.setNom("Pierre");
		staff2.setPrenom("Dupont");
		staff2.setEmail("pierre@dupont.fr");
		staff2.setAge(70);
		staff2.setFonction("Dev");
		staff2.setTelephone("0647382911");
		staff2.setAdresse("25 rue autrepart");
		
		Session session = sessionFactory.openSession(); // On ouvre la session 
		
		// On lance la transaction en SQL
		session.beginTransaction();
		// Sauvegarde du staff créé, et on ne les enchaine PAS
		session.save(staff2);
		// On fait le comit
		session.getTransaction().commit();
		// On ferme la connexion
		session.close();
	}
	
	/**
	 * Permet de recevoir le prénom et le nom d'un employé
	 */
	protected Staff read(int id) {
		Session session = sessionFactory.openSession();
		// Pas besoin de transaction, car pas de modification
		Staff staff = session.get(Staff.class, id);
		System.out.println("L'employé "+staff.getId()+" s'appelle : "+ staff.getNom()+" "+ staff.getPrenom());
		session.close();
		return staff;
	}
	
	/**
	 * Permet de recevoir tous les employés
	 */
	protected List<Staff> readAll() {
		Session session = sessionFactory.openSession();
		
		// AVEC REQUETE HQL
		
		// requete HQL qui récupère tous les employés
		//Query query = session.createQuery("from Staff");
		// liste des staffs récupérés
		//List<Staff> staffs = query.getResultList();
		
		
		// AVEC LE CRITERIA (C'est une requete) BUILDER
		// Je récupère le criteria builder
		CriteriaBuilder cb = session.getCriteriaBuilder();
		// Je créé une requête via mon critéria pour taper sur ma table Staff
		CriteriaQuery<Staff> cq = cb.createQuery(Staff.class);
		// J'ajoute les filtres eventuels (from, where...)
		Root<Staff> root = cq.from(Staff.class);
		// J'insère mes filtres dans ma requête
		CriteriaQuery<Staff> query = cq.select(root);
		// Je balance ma requête dans ma session pour taper ma table et récup la liste de résultat
		List<Staff> result = session.createQuery(query).getResultList();
		
		session.close();
		return result;
	}
	
	/**
	 * Permet de mettre à jour un employé
	 */
	protected void update(int id, Staff newStaff) {
		Staff staff = this.read(id);
		
		// si le nom est différent de null et que le nouveau nom est différent de l'ancien
		if(newStaff.getNom() != null && staff.getNom() != newStaff.getNom()) {
			// on attribut le nouveau nom
			staff.setNom(newStaff.getNom());
		}
		if(newStaff.getPrenom() != null && staff.getPrenom() != newStaff.getPrenom()) {
			staff.setPrenom(newStaff.getPrenom());
		}
		if(newStaff.getEmail() != null && staff.getEmail() != newStaff.getEmail()) {
			staff.setEmail(newStaff.getEmail());
		}
		if(newStaff.getAge() != 0 && staff.getAge() != newStaff.getAge()) {
			staff.setAge(newStaff.getAge());
		}
		if(newStaff.getFonction() != null && staff.getFonction() != newStaff.getFonction()) {
			staff.setFonction(newStaff.getFonction());
		}
		if(newStaff.getTelephone() != null && staff.getTelephone() != newStaff.getTelephone()) {
			staff.setTelephone(newStaff.getTelephone());
		}
		if(newStaff.getAdresse() != null && staff.getAdresse() != newStaff.getAdresse()) {
			staff.setAdresse(newStaff.getAdresse());
		}
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(staff);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Permet de supprimer un employé
	 */
	protected void delete(Staff staff) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(staff);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Fonction main de mon application
	 * où les fonctions seront appelées
	 */
	public static void main(String[] args) {
		
		StaffManager manager = new StaffManager();
		manager.setup();
		//manager.create(); // create fonctionne
		//manager.read(1); // read fonctionne
		
						// Update fonctionne
		
//		Staff staff = new Staff();		
//		staff.setNom("NomChangé!!");
//		staff.setPrenom("Valjean");
//		staff.setEmail("NomChangé!!@jean.fr");
//		staff.setAge(45);
//		staff.setFonction("CEO");
//		staff.setTelephone("0607080910");
//		staff.setAdresse("10 rue qqpart");
//		int id = 2;
//		manager.update(id, staff);	
		
						// readAll fonctionne
		
		List<Staff> staffs = manager.readAll(); 
		for (Staff staff : staffs) {
			System.out.println("L'employé n° "+staff.getId()+" s'appelle "+staff.getNom()+" "+staff.getPrenom() );
		}
		
						//delete fonctionne
		
		//Staff staff = manager.read(1); 
		//manager.delete(staff);
		manager.exit();
	}
}
