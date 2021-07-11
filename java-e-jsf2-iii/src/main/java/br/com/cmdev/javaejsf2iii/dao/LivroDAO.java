package br.com.cmdev.javaejsf2iii.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.cmdev.javaejsf2iii.model.Livro;
import br.com.cmdev.javaejsf2iii.util.annotations.Transactional;

public class LivroDAO {
	
	@Inject
	private EntityManager entityManager;

	@Transactional
	public void gravar(Livro livro) {
		entityManager.persist(livro);
	}

	public List<Livro> listar() {
		String jpql = "SELECT l FROM Livro l ORDER BY l.dataCadastro DESC";
		return entityManager.createQuery(jpql, Livro.class).getResultList();
	}

	public Livro listarPorId(Long idLivro) {
		String jpql = "SELECT l FROM Livro l WHERE l.id = :pIdLivro";
		return entityManager.createQuery(jpql, Livro.class).setParameter("pIdLivro", idLivro).getSingleResult();
	}
	
	@Transactional
	public void atualizar(Livro livro) {
		entityManager.merge(livro);
	}

	@Transactional
	public void apagar(Livro livro) {
		entityManager.remove(entityManager.merge(livro));
	}


}
