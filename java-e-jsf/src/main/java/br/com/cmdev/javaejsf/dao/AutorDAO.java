package br.com.cmdev.javaejsf.dao;

import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.cmdev.javaejsf.model.Autor;

public class AutorDAO {

	public void gravar(Autor autor) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(autor);
		em.getTransaction().commit();
		em.close();
	}

	public List<Autor> listar() {
		EntityManager em = JPAUtil.getEntityManager();
		String jpql = "SELECT a FROM Autor a";
		List<Autor> autorList = em.createQuery(jpql, Autor.class).getResultList();
		autorList.sort(Comparator.comparing(Autor::getNome));
		em.close();
		return autorList;
	}

	public Autor buscarAutor(Long autorId) {
		EntityManager em = JPAUtil.getEntityManager();
		String jpql = "SELECT a FROM Autor a WHERE a.id = :pAutorId";
		Autor autor = em.createQuery(jpql, Autor.class).setParameter("pAutorId", autorId).getSingleResult();
		em.close();
		return autor;
	}

	public void alterar(Autor autor) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(autor);
		em.getTransaction().commit();
		em.close();
	}

	public void apagar(Autor autor) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(autor));
		em.getTransaction().commit();
		em.close();
	}

}
