package nl.hu.ict.dp;

import java.sql.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	private static String orclcfg = "nl.hu.ict.jpa.oracle";
	private static EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) {
		EntityManager em = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory(orclcfg);
			em = entityManagerFactory.createEntityManager();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		// Aanmaken OV Chipkaart domein objecten

		OVChipkaart kaart = new OVChipkaart();
		kaart.setKaartopdruk("mijn eerste kaart");

		// Aanmaken Reiziger domein objecten

		Reiziger r1 = new Reiziger();
		r1.setVoorletters("M");
		r1.setAchternaam("van Dijk");
		r1.setGeboortedatum(Date.valueOf("1998-05-10"));

		// toevoegen in tabel
		Reiziger r2 = new Reiziger();
		r2.setVoorletters("Y");
		r2.setAchternaam("Jansen");
		r2.setGeboortedatum(Date.valueOf("1995-07-02"));

		// r1 updaten
		Reiziger r3 = new Reiziger();
		r3.setVoorletters("M");
		r3.setAchternaam("van Dijk");
		r3.setGeboortedatum(Date.valueOf("1999-09-09"));

		// Opslaan data in domein objecten

		em.getTransaction().begin();
		em.persist(kaart);
		em.persist(r1);
		em.persist(r2);
		em.persist(r3);
		em.getTransaction().commit();

		// Read
		Reiziger r = em.find(Reiziger.class, r1.getReizigerID());
		System.out.println("Reiziger opgehaald: " + r);

		// Update
		Reiziger rr = em.find(Reiziger.class, r2.getReizigerID());
		System.out.println("Voor verandering: " + rr);
		rr.setAchternaam("Test");
		System.out.println("Na verandering: " + rr);

		// Delete
		Reiziger rrr = em.find(Reiziger.class, r3.getReizigerID());
		System.out.println("Voor verwijderen: " + rrr);
		em.remove(r3);

		// OV-Chipkaarten aanmaken en koppelen
		OVChipkaart k1 = new OVChipkaart();
		k1.setReiziger(r2);
		k1.setKaartopdruk("Van r2");

		OVChipkaart k2 = new OVChipkaart();
		k2.setReiziger(r3);
		k2.setKaartopdruk("Van r3");

		OVChipkaart k3 = new OVChipkaart();
		k3.setReiziger(r1);
		k3.setKaartopdruk("Van r1");

		em.getTransaction().begin();
		// CREATE
		em.persist(k1);
		em.persist(k2);
		em.persist(k3);

		// READ
		OVChipkaart k = em.find(OVChipkaart.class, k3.getKaartnr());
		System.out.println("Kaart gelezen: " + k);

		// UPDATE
		k.setKaartopdruk("Test123");

		// DELETE
		em.remove(k3);

		em.close();
		System.out.println("einde");
	}
}