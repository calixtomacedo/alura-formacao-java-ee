package br.com.cmdev.javaeeparteiii.bean;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cmdev.javaeeparteiii.dao.UsuarioDAO;
import br.com.cmdev.javaeeparteiii.model.CarrinhoCompras;
import br.com.cmdev.javaeeparteiii.model.Compra;
import br.com.cmdev.javaeeparteiii.model.Usuario;

@Model
public class CheckoutBean {

	private Usuario usuario;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private CarrinhoCompras carrinho;
	
	@Inject
	private FacesContext context;
	
	@Inject
	private HttpServletRequest request;
	
	public String finalizar() {
		String email = request.getUserPrincipal().getName();
		this.usuario = usuarioDAO.buscarPorEmail(email);
		Compra compra = new Compra();
		compra.setUsuario(usuario);

		context.getExternalContext().getFlash().setKeepMessages(true);
		if(this.usuario == null) {
			context.addMessage(null, new FacesMessage("Usuário não cadastrado. Se increva para concluir a compra."));
			return "/user/usuarioForm?faces-redirect=true";
		}
		
		String finlizado = carrinho.finalizarCompra(compra);
		if(finlizado.equals("OK")) {
			String contextPath = context.getExternalContext().getRequestContextPath();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
			response.setHeader("Location", contextPath.concat("/services/pagamento?uuid=").concat(compra.getUuid()));
		}
		return finlizado;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
