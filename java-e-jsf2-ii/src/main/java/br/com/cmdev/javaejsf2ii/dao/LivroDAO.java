package br.com.cmdev.javaejsf2ii.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.cmdev.javaejsf2ii.model.Livro;

public class LivroDAO {

	public void gravar(Livro livro) throws Exception {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(livro);
		em.getTransaction().commit();
		em.close();
	}

	public List<Livro> listar() throws Exception {
		EntityManager em = JPAUtil.getEntityManager();
		String jpql = "SELECT l FROM Livro l ORDER BY l.dataCadastro DESC";
		List<Livro> livros = em.createQuery(jpql, Livro.class).getResultList();
		em.close();
		return livros;
	}

	public void atualizar(Livro livro) throws Exception {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(livro);
		em.getTransaction().commit();
		em.close();
	}
	
	public void apagar(Livro livro) throws Exception {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(livro));
		em.getTransaction().commit();
		em.close();
	}

}
