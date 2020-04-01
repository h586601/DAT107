package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * @author lph-lokal
 *
 */
public class TodoDAO {
	
	private EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("todoPersistenceUnit");
	
	/**
	 * @param pk
	 * @return
	 */
	public Todo finnTodoMedPk(int pk) {
		
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Todo.class, pk);

		} finally {
			em.close();
		}
	}

	/**
	 * @return
	 */
	public List<Todo> finnAlleTodos() {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			TypedQuery<Todo> query = em.createQuery(
					"SELECT t FROM Todo t", Todo.class);
			
			return query.getResultList();
		
		} finally {
			em.close();
		}
	}

	/**
	 * @param tekst
	 * @return
	 */
	public Todo finnTodoMedTekst(String tekst) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			TypedQuery<Todo> query = em.createQuery(
					"SELECT t FROM Todo t WHERE t.tekst LIKE :tekst", Todo.class);
			query.setParameter("tekst", tekst);
			
			return query.getSingleResult(); //NB! Exception hvis ingen eller flere resultater
		
		} finally {
			em.close();
		}
	}
	
	/**
	 * @param tekst
	 * @return
	 */
	public List<Todo> finnTodosMedTekst(String tekst) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			TypedQuery<Todo> query = em.createQuery(
					"SELECT t FROM Todo t WHERE t.tekst LIKE :tekst", Todo.class);
			query.setParameter("tekst", tekst);
			
			return query.getResultList(); //NB! Tom liste hvis ingen resultat
		
		} finally {
			em.close();
		}
	}

	/**
	 * @param todony
	 */
	public void lagreNyTodo(Todo todony) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			em.persist(todony);
			
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

	/**
	 * @param pk
	 */
	public void slettTodoMedPk(int pk) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			Todo todo = em.find(Todo.class, pk);
			em.remove(todo);
			
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

	/**
	 * @param todo
	 */
	public void oppdaterTodo(Todo todo) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			em.merge(todo);
			
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

	/**
	 * @param id
	 * @param tekst
	 */
	public void oppdaterTekst(int id, String tekst) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Todo managedTodo = em.find(Todo.class, id);
			//NB!: Kan ikke gjøre:
			//     Todo managedTodo = finnTodoMedPk(id);
			
			managedTodo.setTekst(tekst);
			
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
