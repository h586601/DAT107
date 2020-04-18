package no.hvl.dat107.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;


public class AvdelingDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
	
	
	public Avdeling finnAvdelingMedId(int id) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			return em.find(Avdeling.class, id);
		} finally {
			em.close();
		}
	}
	
	public void skrivUtAvdelingsliste(int avdelingID) {
		 
		EntityManager em = emf.createEntityManager();
		
		List<Ansatt> ansatte = null;
		
		try {
			
			
			String jpql = "SELECT k FROM Ansatt k " +
	        		"WHERE k.avdeling.avdelingID = :avdID";
			
			TypedQuery<Ansatt> query = em.createQuery(jpql, Ansatt.class);
			query.setParameter("avdID", avdelingID);
			ansatte = query.getResultList();
			
			for(int pos = 0; pos < ansatte.size(); pos++) {
				System.out.println(ansatte.get(pos).toString());
			}
			
		} finally {
			em.close();
		}
	}
	
	public void leggInnNy(String avdNavn, int avdelingsleder) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			
			Avdeling avd = new Avdeling();
			avd.setAvdNavn(avdNavn);
			avd.setAvdelingsleder(avdelingsleder);
			
			em.persist(avd);
			
			int ansattID = avdelingsleder;
			Ansatt ans = em.find(Ansatt.class, ansattID);
			ans.setAvdeling(avd);
			
			tx.commit();
			
		} catch (Throwable t) {
			if(tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
}
