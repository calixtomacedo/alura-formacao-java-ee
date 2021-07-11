package br.com.cmdev.javaejsf2iii.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.cmdev.javaejsf2iii.model.Autor;
import br.com.cmdev.javaejsf2iii.util.annotations.Transactional;

public class AutorDAO {

	@Inject
	private EntityManager entityManager;
	
	@Transactional
	public void gravar(Autor autor) {
		entityManager.persist(autor);
	}

	public List<Autor> listar() {
		String jpql = "SELECT a FROM Autor a ORDER BY a.dataCadastro DESC";
		List<Autor> autorList = entityManager.createQuery(jpql, Autor.class).getResultList();
		return autorList;
	}

	public Autor buscarAutor(Long autorId) {
		String jpql = "SELECT a FROM Autor a WHERE a.id = :pAutorId";
		Autor autor = entityManager.createQuery(jpql, Autor.class).setParameter("pAutorId", autorId).getSingleResult();
		return autor;
	}

	@Transactional
	public void alterar(Autor autor) {
		entityManager.merge(autor);
	}

	@Transactional
	public void apagar(Autor autor) {
		entityManager.remove(entityManager.merge(autor));
	}

}
