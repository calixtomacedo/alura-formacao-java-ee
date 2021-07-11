package br.com.cmdev.javaeeparteiii.utils.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.cmdev.javaeeparteiii.dao.UsuarioDAO;
import br.com.cmdev.javaeeparteiii.model.Usuario;

@Model
public class CurrentUser {

	private Usuario user;

	@Inject
	private HttpServletRequest request;
	
	@Inject
	private UsuarioDAO dao;
	

	public Usuario get() {
		return user;
	}
	
	@PostConstruct
	public void loadUserPrincipal() {
		Principal principal = request.getUserPrincipal();
		if(principal != null) {
			String email = request.getUserPrincipal().getName();
			user = dao.buscarPorEmail(email);
		}
	}
	
	public Boolean hasRole(String role) {
		return request.isUserInRole(role);
	}
	
	public boolean isUserLoggedIn() {
	    String user = this.getUsername();
	    boolean result = !((user == null) || user.isEmpty());
	    return result;
	}

	public String getUsername() {
	    String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
	    return user;
	}
	
}
