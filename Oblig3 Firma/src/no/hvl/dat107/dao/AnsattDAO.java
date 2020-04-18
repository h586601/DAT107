package no.hvl.dat107.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.Prosjektdeltagelse;

public class AnsattDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");

	public Ansatt finnAnsattMedId(int id) {

		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Ansatt.class, id);
		} finally {
			em.close();
		}
	}

	public Ansatt finnAnsattMedBrukernavn(String brukernavn) {

		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT t FROM Ansatt t WHERE t.brukernavn = :brukernavn",
					Ansatt.class);
			query.setParameter("brukernavn", brukernavn);

			return query.getSingleResult();
		} finally {
			em.close();
		}

	}

	public void utskrivAlleAnsatte() {

		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT t FROM Ansatt t", Ansatt.class);
			List<Ansatt> liste = query.getResultList();

			for(int pos = 0; pos < liste.size(); pos++) {
				System.out.println(liste.get(pos).toString());
			}
			
		} finally {
			em.close();
		}

	}
	
	public void oppdater(Ansatt ansatt) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			em.merge(ansatt);
			
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if(tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
	
	public void leggInnAnsatt(Ansatt ansatt) {
		 EntityManager em = emf.createEntityManager();
		 EntityTransaction tx = em.getTransaction();
		 
		 try {
			 tx.begin();
			 
			 em.persist(ansatt);
			 
			 tx.commit();
		 } catch(Throwable e) {
			 e.printStackTrace();
			 if(tx.isActive())
				 tx.rollback();
		 } finally {
			 em.close();
		 }
		 
	}
	
	public void leggInnNy(String brukernavn, String fornavn, String etternavn, LocalDate ansDato, String stilling,
			BigDecimal mndsLonn, int avdID) {
		
		 EntityManager em = emf.createEntityManager();
		 EntityTransaction tx = em.getTransaction();
		 
		 Ansatt a = null;
		 
		 try {
			 tx.begin();
			 
			 Avdeling avd = em.find(Avdeling.class, avdID);
			 a = new Ansatt(brukernavn, fornavn, etternavn, ansDato, stilling, mndsLonn, avd);
			 
			 em.persist(a);
			 
			 tx.commit();
			 
		 } finally {
			 em.close();
		 }
	}
	
	public void oppdaterAvdeling(int ansattID, int avdID) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		String jpql = "SELECT k FROM Ansatt k " + 
				"WHERE k.ansattID = :ansattID AND k.avdeling.avdelingsleder != :ansattID";
		
//		Ansatt a = em.find(Ansatt.class, ansattID);
		Avdeling avd = em.find(Avdeling.class, avdID);
		
		try {
			tx.begin();
			
			TypedQuery<Ansatt> query = em.createQuery(jpql, Ansatt.class);
			query.setParameter("ansattID", ansattID);
			Ansatt a = query.getSingleResult();
			a.setAvdeling(avd);
			em.merge(a);
			
			tx.commit();
		} finally {
			em.close();
		}
	}
	
public void registrerProsjektdeltagelse(Ansatt a, Prosjekt p, String rolle, int timer) {
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            a = em.merge(a);
            p = em.merge(p);
            
            Prosjektdeltagelse pd = new Prosjektdeltagelse(a, p, rolle ,timer);
            
            a.leggTilProsjektdeltagelse(pd);
            p.leggTilProsjektdeltagelse(pd);
            
            em.persist(pd);
            
            tx.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
        
    }
}
