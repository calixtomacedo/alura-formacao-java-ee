package br.com.cmdev.javaejsf2ii.business;

import br.com.cmdev.javaejsf2ii.dao.UsuarioDAO;
import br.com.cmdev.javaejsf2ii.model.Usuario;

public class UsuarioBusiness {

	public Boolean autenticar(Usuario usuario) throws Exception {
		Usuario autenticado = new UsuarioDAO().autenticar(usuario);
		if(autenticado != null && autenticado.getLogin().equals(usuario.getLogin()) && autenticado.getSenha().equals(usuario.getSenha())) {
			preencherDadosUsuarioAutenticado(usuario, autenticado);

			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	private void preencherDadosUsuarioAutenticado(Usuario usuario, Usuario autenticado) {
		usuario.setId(autenticado.getId());
		usuario.setNome(autenticado.getNome());
		usuario.setEmail(autenticado.getEmail());
		usuario.setLogin(autenticado.getLogin());
	}

}
