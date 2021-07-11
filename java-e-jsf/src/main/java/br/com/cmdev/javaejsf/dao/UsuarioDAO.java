package br.com.cmdev.javaejsf.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.cmdev.javaejsf.model.Usuario;

public class UsuarioDAO {

	public Usuario autenticar(Usuario usuario) {
		Usuario usuarioAutenticado = null;
		EntityManager em = JPAUtil.getEntityManagerCmdev();
		String jpql = "SELECT u FROM Usuario u WHERE u.login = :pLogin AND u.senha = :pSenha AND u.flagAtivo = true";
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			usuarioAutenticado = query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		em.close();
		return usuarioAutenticado;
	}

}
