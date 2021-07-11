package br.com.cmdev.javaeeparteiii.bean;

import java.time.LocalDateTime;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.cmdev.javaeeparteiii.dao.UsuarioDAO;
import br.com.cmdev.javaeeparteiii.model.Usuario;

@Model
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	public String salvar() {
		usuario.setDataCadastro(LocalDateTime.now());
		usuario.setFlagAtivo(Boolean.TRUE);
		usuarioDAO.salvar(usuario);
		this.usuario = new Usuario();
		return "/pages/home?faces-redirect=true";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
