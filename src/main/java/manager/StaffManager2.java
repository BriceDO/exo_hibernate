package manager;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entities.Entreprise;
import entities.Filiale;
import entities.Secteur;
import entities.Staff;

public class StaffManager2 {

	public static void main(String[] args) {
		
		//   DEBUT DE SESSION   //
		
		SessionFactory sessionFactory; 
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();   
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession(); // Ouverture de session
        session.beginTransaction();
        
	    // CREATION D'ENTREPRISE //
	    
        Entreprise entreprise1 = new Entreprise(); 
        entreprise1.setNom("SEMIFIR");
        
        // CREATION DE SECTEUR //
        
	    Secteur secteur1 = new Secteur(); 
	    secteur1.setLocalisation("Lille");

        // CREATION DE FILIALE //
        
        Filiale filiale1 = new Filiale(); 
        filiale1.setNom("TOTOTATA");
        filiale1.setNb_employee(2);
        
        // CREATION EMPLOYEE //
        
        Staff staff1 = new Staff(); 
		staff1.setNom("Jean");
		staff1.setPrenom("Valjean");
		staff1.setEmail("jean@jean.fr");
		staff1.setAge(45);
		staff1.setFonction("CEO");
		staff1.setTelephone("0607080910");
		staff1.setAdresse("10 rue qqpart");
		staff1.setSecteur(secteur1);
		
		Staff staff2 = new Staff();
		staff2.setNom("Pierre");
		staff2.setPrenom("Dupont");
		staff2.setEmail("pierre@dupont.fr");
		staff2.setAge(70);
		staff2.setFonction("Dev");
		staff2.setTelephone("0647382911");
		staff2.setAdresse("25 rue autrepart");
		staff2.setSecteur(secteur1);
		
		// SET STAFFS //
		
		Set<Staff> staffs = new HashSet<Staff>();
		staffs.add(staff1);
		staffs.add(staff2);
        
		// SET FILIALES //
		
		Set<Filiale> filiales = new HashSet<Filiale>();
		filiales.add(filiale1);
		secteur1.setFiliales(filiales);
		
        filiale1.setEntreprise(entreprise1);
        
        // SET SECTEURS //
        
		Set<Secteur> secteurs = new HashSet<Secteur>();
		secteurs.add(secteur1);
		filiale1.setSecteurs(secteurs);
		
        entreprise1.setFiliales(filiales);
		secteur1.setStaffs(staffs);

        filiale1.addSecteur(secteur1);
        secteur1.addFiliale(filiale1);
        
        // SAVE //
        
        session.save(secteur1);
        session.save(entreprise1);
        
        // FIN DE SESSION //
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();		
	}
}