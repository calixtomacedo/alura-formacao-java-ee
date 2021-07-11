package br.com.cmdev.javaejsf.business;

import br.com.cmdev.javaejsf.dao.UsuarioDAO;
import br.com.cmdev.javaejsf.model.Usuario;

public class UsuarioBusiness {

	public Boolean autenticar(Usuario usuario) {
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
