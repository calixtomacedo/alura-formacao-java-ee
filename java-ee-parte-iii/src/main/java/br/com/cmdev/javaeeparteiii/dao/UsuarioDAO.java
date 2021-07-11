package br.com.cmdev.javaeeparteiii.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.cmdev.javaeeparteiii.model.Usuario;

public class UsuarioDAO {
	
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void salvar(Usuario usuario) {
		manager.persist(usuario);
	}

	public Usuario buscarUsuario(Usuario usuario) {
		Usuario response = null;
		String jpql = "SELECT u FROM Usuario u WHERE u.email = :pEmail AND u.senha = :pSenha";
		try {
			response = manager.createQuery(jpql, Usuario.class).setParameter("pEmail", usuario.getEmail()).setParameter("pSenha", usuario.getSenha()).getSingleResult();
		} catch (Exception e) {
			response = null;
		}
		return response;
	}

	public Usuario buscarPorEmail(String email) {
		String jpql = "SELECT u FROM Usuario u WHERE u.email = :pEmail";
		return manager.createQuery(jpql, Usuario.class).setParameter("pEmail", email).getSingleResult();
	}
	
}
