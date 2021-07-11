package br.com.cmdev.javaejsf2iii.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.cmdev.javaejsf2iii.model.Venda;
import br.com.cmdev.javaejsf2iii.util.annotations.Transactional;

public class VendaDAO {

	@Inject
	private EntityManager entityManager;
	
	@Transactional
	public void gravar(Venda venda) {
		entityManager.persist(venda);
	}
	
	public List<Venda> listar(){
		String jpql = "SELECT v FROM Venda v";
		return entityManager.createQuery(jpql, Venda.class).getResultList();
	}

}
