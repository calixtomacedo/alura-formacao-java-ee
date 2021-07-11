package br.com.cmdev.javaejsf2ii.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.cmdev.javaejsf2ii.model.Usuario;

public class UsuarioDAO {

	public Usuario autenticar(Usuario usuario) throws Exception {
		Usuario usuarioAutenticado = null;
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerCmdev();
		} catch (Exception e) {
			throw new Exception("Ocorreu erro ao conectar ao banco de dados: ".concat(e.getMessage()));
		}
		
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
