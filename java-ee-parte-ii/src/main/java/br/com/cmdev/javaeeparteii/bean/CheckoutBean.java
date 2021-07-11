package br.com.cmdev.javaeeparteii.bean;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import br.com.cmdev.javaeeparteii.dao.UsuarioDAO;
import br.com.cmdev.javaeeparteii.model.CarrinhoCompras;
import br.com.cmdev.javaeeparteii.model.Compra;
import br.com.cmdev.javaeeparteii.model.Usuario;

@Model
public class CheckoutBean {

	private Usuario usuario = new Usuario();
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private CarrinhoCompras carrinho;
	
	@Inject
	private FacesContext context;
	
	public String finalizar() {
		this.usuario = usuarioDAO.buscarUsuario(usuario);
		Compra compra = new Compra();
		compra.setUsuario(usuario);

		context.getExternalContext().getFlash().setKeepMessages(true);
		if(this.usuario == null) {
			context.addMessage(null, new FacesMessage("Usuário não cadastrado. Se increva para concluir a compra."));
			return "/user/usuarioForm?faces-redirect=true";
		}
		carrinho.finalizarCompra(compra);
		
		String contextPath = context.getExternalContext().getRequestContextPath();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		response.setHeader("Location", contextPath.concat("/services/pagamento?uuid=").concat(compra.getUuid()));

		return "";
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
