package no.hvl.dat107;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FirmaMain {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		Ansatt ansatt = null;
		
		try {
			
			ansatt = em.find(Ansatt.class, 1);
			
		} finally {
			em.close();
		}
		
		ansatt.skrivUt();
	}

}
