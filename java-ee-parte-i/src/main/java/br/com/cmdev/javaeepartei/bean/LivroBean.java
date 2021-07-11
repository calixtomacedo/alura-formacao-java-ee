package br.com.cmdev.javaeepartei.bean;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import br.com.cmdev.javaeepartei.dao.AutorDAO;
import br.com.cmdev.javaeepartei.dao.LivroDAO;
import br.com.cmdev.javaeepartei.model.Autor;
import br.com.cmdev.javaeepartei.model.Livro;
import br.com.cmdev.javaeepartei.utils.infra.FileSaver;

@Named
@RequestScoped
public class LivroBean {

	@Inject
	private LivroDAO livroDAO;
	
	@Inject
	private AutorDAO autorDAO;
	
	@Inject
	private FacesContext context;

	private Livro livro = new Livro();
	
	private Part capaLivro;

	public String salvar() throws IOException {
		this.livro.setDataCadastro(LocalDateTime.now());
		this.livro.setCapaPath(new FileSaver().write(capaLivro, "livros"));
		livroDAO.salvar(livro);
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso!"));
		
		return "/livros/lista?faces-redirect=true";
	}

	public List<Autor> getAutores() {
		return autorDAO.listar();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Part getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}

}
