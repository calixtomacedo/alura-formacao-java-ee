package br.com.cmdev.javaeeparteiii.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cmdev.javaeeparteiii.model.Autor;

public class AutorDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Autor> listar() {
		return manager.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
	}

}
