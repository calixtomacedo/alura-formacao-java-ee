package br.com.cmdev.javaejsf2iii.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.cmdev.javaejsf2iii.model.Usuario;

public class Autorizador implements PhaseListener {

	private final String pageLogin = "/login.xhtml";

	private static final long serialVersionUID = -593005399547419875L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		String pageName = context.getViewRoot().getViewId();
		if (pageLogin.equals(pageName)) {
			return;
		}

		Usuario usuarioAutenticado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioAutenticado");
		if (usuarioAutenticado != null) {
			return;
		}
		
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "/login?faces-redirect=true");
		context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
