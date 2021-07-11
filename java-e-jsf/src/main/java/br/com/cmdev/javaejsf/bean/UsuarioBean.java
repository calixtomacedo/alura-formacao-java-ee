package br.com.cmdev.javaejsf.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cmdev.javaejsf.business.UsuarioBusiness;
import br.com.cmdev.javaejsf.model.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public String autenticar() {
		
		Boolean usuarioAutenticado = new UsuarioBusiness().autenticar(this.usuario);

		FacesContext context = FacesContext.getCurrentInstance();
		
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
