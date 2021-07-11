package br.com.cmdev.javaeeparteiii.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.cmdev.javaeeparteiii.dao.LivroDAO;
import br.com.cmdev.javaeeparteiii.model.Livro;

@Model
public class DetalheBean {
	
	@Inject
	private LivroDAO livroDAO;

	private Livro livro;
	private Integer idLivro;
	
	public void carregaDetalhe() {
		this.livro = livroDAO.buscarPorId(idLivro);
	}

	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}
	
	
	
}
