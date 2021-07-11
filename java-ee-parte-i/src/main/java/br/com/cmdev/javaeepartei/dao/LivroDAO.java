package br.com.cmdev.javaeepartei.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.cmdev.javaeepartei.model.Livro;

public class LivroDAO {

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void salvar(Livro livro) {
		manager.persist(livro);
	}

	public List<Livro> listar() {
		String jpql = "SELECT DISTINCT(l) FROM Livro l JOIN fetch l.autores ";
		return manager.createQuery(jpql, Livro.class).getResultList();
	}
}
