package br.com.cmdev.javaejsf.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.cmdev.javaejsf.business.AutorBusiness;
import br.com.cmdev.javaejsf.business.LivroBusiness;
import br.com.cmdev.javaejsf.model.Autor;
import br.com.cmdev.javaejsf.model.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Long autorId;

	public Livro getLivro() {
		return livro;
	}

	public List<Autor> getAutores() {
		return new AutorBusiness().listar();
	}

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public String formAutor() {
		return "autor?faces-redirect=true";
	}
	
	public void adicionarAutor() {
		Autor autor = new AutorBusiness().buscarAutor(this.autorId);
		this.livro.setAutores(autor);
	}
	
	public void removeAutor(Autor autor) {
		this.livro.remove(autor);
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}
	
	public void validarIsbn(FacesContext fc, UIComponent component, Object object) throws ValidatorException {
		if(!object.toString().startsWith("1")) {
			throw new ValidatorException(new FacesMessage("O campo ISBN deve começar o o caractér 1."));
		}
	}

	public void gravar() {
		if (livro.getAutores().isEmpty()) {
			//throw new RuntimeException("Livro deve ter pelo menos um Autor.");
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}
		if(this.livro.getId() == null) {
			new LivroBusiness().gravar(livro);
		}else {
			new LivroBusiness().atualizar(livro);
		}
		this.livro = new Livro();
		this.autorId = null;
	}
	
	public List<Livro> listar(){
		return new LivroBusiness().listar();
	}
	
	public void alterar(Livro livro) {
		this.livro = livro;
	}
	
	public void apagar(Livro livro) {
		new LivroBusiness().apagar(livro);
	}

}
