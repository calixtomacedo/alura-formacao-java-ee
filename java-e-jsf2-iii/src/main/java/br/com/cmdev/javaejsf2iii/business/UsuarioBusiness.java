package br.com.cmdev.javaejsf2iii.business;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.cmdev.javaejsf2iii.dao.UsuarioDAO;
import br.com.cmdev.javaejsf2iii.model.Usuario;

public class UsuarioBusiness implements Serializable {

	private static final long serialVersionUID = 1966404675421927835L;

	@Inject
	private UsuarioDAO usuarioDAO;
	
	public Boolean autenticar(Usuario usuario) throws Exception {
		Usuario autenticado = usuarioDAO.autenticar(usuario);
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
