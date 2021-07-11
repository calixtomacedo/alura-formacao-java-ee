package br.com.cmdev.javaejsf2iii.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cmdev.javaejsf2iii.business.UsuarioBusiness;
import br.com.cmdev.javaejsf2iii.model.Usuario;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 3129577870154680494L;
	
	@Inject
	private UsuarioBusiness usuarioBusiness;
	
	@Inject
	private FacesContext context;
	
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	
	public String autenticar() {
		Boolean usuarioAutenticado = null;
		try {
			usuarioAutenticado = usuarioBusiness.autenticar(this.usuario);
		} catch (Exception e) {
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage(e.getMessage() != null ? e.getMessage() : "Fallha na autenticação"));
			return "/login?faces-redirect=true";
		}
		
		if (usuarioAutenticado) {
			context.getExternalContext().getSessionMap().put("usuarioAutenticado", this.usuario);
			return "/pages/livro?faces-redirect=true";
		}
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não cadastrado no sistema."));
		
		return "/login?faces-redirect=true";
	}

	public String logout() {
		context.getExternalContext().getSessionMap().remove("usuarioAutenticado");
		return "/login?faces-redirect=true";
	}

}
