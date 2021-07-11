package br.com.cmdev.javaeeparteii.bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.cmdev.javaeeparteii.dao.LivroDAO;
import br.com.cmdev.javaeeparteii.model.Livro;

@Model
public class ListalivrosBean {

	private List<Livro> livros = new ArrayList<Livro>();

	@Inject
	private LivroDAO livroDAO;

	public List<Livro> getLivros() {
		this.livros = livroDAO.listar();
		return livros;
	}

}
