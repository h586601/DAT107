package no.hvl.dat107.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import no.hvl.dat107.entity.Prosjekt;

public class ProsjektDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
	
	public Prosjekt finnProsjektMedId(int id) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			return em.find(Prosjekt.class, id);
		} finally {
			em.close();
		}
	}
	
	public void leggTilProsjekt(String prosjektnavn, String beskrivelse) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Prosjekt p = null;
		
		try {
			tx.begin();
			
			p = new Prosjekt(prosjektnavn, beskrivelse);
			em.persist(p);
			
			tx.commit();
		} catch (Throwable e) {
			if(tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
	
}
