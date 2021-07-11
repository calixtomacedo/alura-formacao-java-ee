package br.com.cmdev.javaeeparteiii.utils.security;

import java.io.Serializable;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.cmdev.javaeeparteiii.model.Usuario;

@Model
public class UserAutentication implements Serializable {

	private static final long serialVersionUID = -601095548642851153L;

	private Usuario user = new Usuario();

	@Inject
	private HttpServletRequest request;
	
	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		String redirectPage = "/pages/home?faces-redirect=true";
		try {
			request = (HttpServletRequest) context.getExternalContext().getRequest();
			request.login(user.getEmail(), user.getSenha());
		} catch (ServletException e) {
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Falha na autênticação, tente novamente"));
			redirectPage = "/login?faces-redirect=true";
		}
		return redirectPage;
	}
	
	public String logout() {
	    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	    session.invalidate();
	    return "/pages/home?faces-redirect=true";
	}
	
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}  
	
}
