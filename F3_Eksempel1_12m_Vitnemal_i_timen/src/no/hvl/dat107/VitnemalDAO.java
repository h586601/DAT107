package no.hvl.dat107;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class VitnemalDAO {

    private EntityManagerFactory emf;

    public VitnemalDAO() {
        emf = Persistence.createEntityManagerFactory("vitnemalPU");
    }

    public Vitnemal finnVitnemalForStudent(int studNr) {
        
        EntityManager em = emf.createEntityManager();

        try {
        	// TODO

        } finally {
            em.close();
        }
    	return null;
    }

    public Karakter registrerKarakterForStudent(int studNr, String emnekode, 
    		LocalDate eksDato, String bokstav) {
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            
        	// TODO
            
            tx.commit();
            
        } catch (Throwable e) {
        	if (tx.isActive()) {
                tx.rollback();
        	}
        } finally {
            em.close();
        }
    	return null;
    }

    public Karakter finnKarakter(int studNr, String emnekode) {
        
        EntityManager em = emf.createEntityManager();

        try {
        	// TODO

        } finally {
            em.close();
        }
    	return null;
    }
}
