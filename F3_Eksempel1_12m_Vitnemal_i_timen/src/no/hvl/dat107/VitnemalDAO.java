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
        	return em.find(Vitnemal.class, studNr);

        } finally {
            em.close();
        }
    }

    public Karakter registrerKarakterForStudent(int studNr, String emnekode, 
    		LocalDate eksDato, String bokstav) {
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        Karakter k = null;
        
        try {
            tx.begin();
            
//            Vitnemal v = finnVitnemalForStudent(studNr);  // v er her detached fra pc!
            Vitnemal v = em.find(Vitnemal.class, studNr); // v er her managed i pc !
            
            k = new Karakter(emnekode, eksDato, bokstav, v);
            
            em.persist(k);
            
            v.addKarakter(k);
            
            tx.commit();
            
        } catch (Throwable e) {
        	if (tx.isActive()) {
                tx.rollback();
        	}
        } finally {
            em.close();
        }
        return k;
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
