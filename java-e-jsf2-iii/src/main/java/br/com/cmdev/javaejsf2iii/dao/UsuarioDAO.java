package br.com.cmdev.javaejsf2iii.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.cmdev.javaejsf2iii.model.Usuario;
import br.com.cmdev.javaejsf2iii.util.annotations.CmdevDB;

public class UsuarioDAO {

	@Inject
	@CmdevDB
	private EntityManager entityManager;
	
	public Usuario autenticar(Usuario usuario) throws Exception {
		
		Usuario usuarioAutenticado = null;

		String jpql = "SELECT u FROM Usuario u WHERE u.login = :pLogin AND u.senha = :pSenha AND u.flagAtivo = true";
		TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());

		try {
			usuarioAutenticado = query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return usuarioAutenticado;
	}

}
