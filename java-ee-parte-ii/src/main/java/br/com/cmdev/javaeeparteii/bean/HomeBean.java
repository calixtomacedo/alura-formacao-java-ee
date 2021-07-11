package br.com.cmdev.javaeeparteii.bean;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.cmdev.javaeeparteii.dao.LivroDAO;
import br.com.cmdev.javaeeparteii.model.Livro;

@Model
public class HomeBean {

	@Inject
	private LivroDAO livroDAO;
	
	private Livro livro;

	public List<Livro> ultimosLancamentos(){
		return livroDAO.ultimosLancamentos();
	}
	
	public List<Livro> demaisLivros(){
		return livroDAO.demaisLivros();
	}
	
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
}
