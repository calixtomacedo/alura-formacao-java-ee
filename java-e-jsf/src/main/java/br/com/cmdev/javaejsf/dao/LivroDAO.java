package br.com.cmdev.javaejsf.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.cmdev.javaejsf.model.Livro;

public class LivroDAO {

	public void gravar(Livro livro) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(livro);
		em.getTransaction().commit();
		em.close();
	}

	public List<Livro> listar() {
		EntityManager em = JPAUtil.getEntityManager();
		String jpql = "SELECT l FROM Livro l";
		List<Livro> livros = em.createQuery(jpql, Livro.class).getResultList();
		em.close();
		return livros;
	}

	public void atualizar(Livro livro) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(livro);
		em.getTransaction().commit();
		em.close();
	}
	
	public void apagar(Livro livro) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(livro));
		em.getTransaction().commit();
		em.close();
	}

}
