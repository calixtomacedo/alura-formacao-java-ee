package br.com.cmdev.javaejsf.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cmdev.javaejsf.business.AutorBusiness;
import br.com.cmdev.javaejsf.model.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Long autorId;
	
	Autor autor = new Autor();
	
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
		this.autor = new AutorBusiness().buscarAutor(autorId);
	}

	public String gravar() {
		if(this.autor.getId() == null) {
			new AutorBusiness().gravar(autor);
		}else {
			new AutorBusiness().alterar(autor);
		}
		this.autor = new Autor();
		return "livro?faces-redirect=true";
	}
	
	public List<Autor> listar(){
		return new AutorBusiness().listar();
	}
	
	public void alterar(Autor autor) {
		this.autor = autor;
	}
	
	public void apagar(Autor autor) {
		new AutorBusiness().apagar(autor);
	}
	
}
