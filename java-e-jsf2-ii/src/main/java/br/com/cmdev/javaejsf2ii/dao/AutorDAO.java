package br.com.cmdev.javaejsf2ii.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.cmdev.javaejsf2ii.model.Autor;

public class AutorDAO {

	public void gravar(Autor autor) throws Exception {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(autor);
		em.getTransaction().commit();
		em.close();
	}

	public List<Autor> listar() throws Exception {
		EntityManager em = JPAUtil.getEntityManager();
		String jpql = "SELECT a FROM Autor a ORDER BY a.dataCadastro DESC";
		List<Autor> autorList = em.createQuery(jpql, Autor.class).getResultList();
		em.close();
		return autorList;
	}

	public Autor buscarAutor(Long autorId) throws Exception {
		EntityManager em = JPAUtil.getEntityManager();
		String jpql = "SELECT a FROM Autor a WHERE a.id = :pAutorId";
		Autor autor = em.createQuery(jpql, Autor.class).setParameter("pAutorId", autorId).getSingleResult();
		em.close();
		return autor;
	}

	public void alterar(Autor autor) throws Exception {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(autor);
		em.getTransaction().commit();
		em.close();
	}

	public void apagar(Autor autor) throws Exception {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(autor));
		em.getTransaction().commit();
		em.close();
	}

}
