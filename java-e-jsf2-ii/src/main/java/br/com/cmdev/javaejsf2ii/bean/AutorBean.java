package br.com.cmdev.javaejsf2ii.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cmdev.javaejsf2ii.business.AutorBusiness;
import br.com.cmdev.javaejsf2ii.model.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Long autorId;

	private Autor autor = new Autor();

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public Autor getAutor() {
		return autor;
	}

	public void carregarAutorPorId() throws Exception {
		this.autor = new AutorBusiness().buscarAutor(autorId);
	}

	public void gravar() throws Exception {
		if (this.autor.getId() == null) {
			new AutorBusiness().gravar(autor);
		} else {
			new AutorBusiness().alterar(autor);
		}
		this.autor = new Autor();
		//return "livro?faces-redirect=true";
	}

	public List<Autor> listar() throws Exception {
		return new AutorBusiness().listar();
	}

	public void alterar(Autor autor) {
		this.autor = autor;
	}

	public void apagar(Autor autor) throws Exception {
		addMessage("Confirmed", "Record deleted");
		new AutorBusiness().apagar(autor);
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
