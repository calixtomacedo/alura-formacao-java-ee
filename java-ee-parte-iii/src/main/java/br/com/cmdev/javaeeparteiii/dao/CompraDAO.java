package br.com.cmdev.javaeeparteiii.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.cmdev.javaeeparteiii.model.Compra;

public class CompraDAO implements Serializable {

	private static final long serialVersionUID = 676401578201538018L;

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void salvar(Compra compra) {
		manager.persist(compra);
	}

	public Compra buscarPorUuId(String uuid) {
		String jpql = "SELECT c FROM Compra c WHERE c.uuid = :pUuid";
		return manager.createQuery(jpql, Compra.class).setParameter("pUuid", uuid).getSingleResult();
	}

}
