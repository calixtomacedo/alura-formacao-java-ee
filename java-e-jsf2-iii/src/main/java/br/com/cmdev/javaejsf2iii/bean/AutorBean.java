package br.com.cmdev.javaejsf2iii.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cmdev.javaejsf2iii.business.AutorBusiness;
import br.com.cmdev.javaejsf2iii.model.Autor;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 7597127325452604981L;
	
	@Inject
	private AutorBusiness autorBusiness;

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

	public void carregarAutorPorId() {
		this.autor = autorBusiness.buscarAutor(autorId);
	}

	public void gravar() {
		if (this.autor.getId() == null) {
			autorBusiness.gravar(autor);
		} else {
			autorBusiness.alterar(autor);
		}
		this.autor = new Autor();
		//return "livro?faces-redirect=true";
	}

	public List<Autor> listar() {
		return autorBusiness.listar();
	}

	public void alterar(Autor autor) {
		this.autor = autor;
	}

	public void apagar(Autor autor) {
		autorBusiness.apagar(autor);
	}

}
