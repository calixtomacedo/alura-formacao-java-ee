package br.com.cmdev.javaeeparteii.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.cmdev.javaeeparteii.dao.LivroDAO;
import br.com.cmdev.javaeeparteii.model.Livro;

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
