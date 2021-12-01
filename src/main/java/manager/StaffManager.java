package manager;
import java.util.List;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entities.Staff;

/**
 * Va g�rer les requ�tes � la base de donn�es
 * gr�ce � hibernate
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
		
		// On va essayer de se connecter � la BDD avec un try
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		// Le catch (donc si co impossible) va detruire le registre initialis�
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
			e.getStackTrace();
		}
	}
	
	/*
	 * Permet de supprimer une session Hibernate
	 */
	protected void exit() {
		sessionFactory.close();
	}
	
	/**
	 * Permet de cr�er des employ�s dans ma BDD
	 */
	protected void create() {
	
		Staff staff1 = new Staff(); // Cr�ation d'un employ�
		staff1.setNom("Jean");
		staff1.setPrenom("Valjean");
		staff1.setEmail("jean@jean.fr");
		staff1.setAge(45);
		staff1.setFonction("CEO");
		staff1.setTelephone("0607080910");
		staff1.setAdresse("10 rue qqpart");
		
		Staff staff2 = new Staff(); // Cr�ation d'un employ�
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
		// Sauvegarde du staff cr��, et on ne les enchaine PAS
		session.save(staff2);
		// On fait le comit et on close
		session.getTransaction().commit();
		// On ferme la connexion
		session.close();
	}
	
	/**
	 * Permet de recevoir les infos d'un employ�
	 */
	protected Staff read(int id) {
		Session session = sessionFactory.openSession();
		// Pas besoin de transaction, car pas de modification
		Staff staff = session.get(Staff.class, id);
		System.out.println("L'employ� "+staff.getId()+" s'appelle : "+ staff.getNom()+" "+ staff.getPrenom());
		session.close();
		return staff;
	}
	
	/**
	 * Permet de recevoir les infos de tous les employ�s
	 */
	protected List<Staff> readAll() {
		Session session = sessionFactory.openSession();
		
		Query query = session.createQuery("select nom, prenom from staff");
		
		
		return listeStaff;
	}
	
	/**
	 * Permet de mettre � jour un employ�
	 */
	protected void update(int id, Staff newStaff) {
		
	}
	
	/**
	 * Permet de supprimer un employ�
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
	 * o� les fonctions seront appel�es
	 */
	public static void main(String[] args) {
		
		StaffManager manager = new StaffManager();
		manager.setup();
		//manager.create(); // create fonctionne
		//manager.read(1); // read fonctionne
		
		
		//Staff staff = manager.read(1); delete fonctionne
		//manager.delete(staff);
	}
}
