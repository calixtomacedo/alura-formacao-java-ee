package br.com.cmdev.javaejsf2ii.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cmdev.javaejsf2ii.business.UsuarioBusiness;
import br.com.cmdev.javaejsf2ii.model.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public String autenticar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Boolean usuarioAutenticado = null;
		try {
			usuarioAutenticado = new UsuarioBusiness().autenticar(this.usuario);
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
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioAutenticado");
		return "/login?faces-redirect=true";
	}

}
