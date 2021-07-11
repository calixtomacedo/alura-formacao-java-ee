package br.com.cmdev.javaejsf2iii.business;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import br.com.cmdev.javaejsf2iii.dao.LivroDAO;
import br.com.cmdev.javaejsf2iii.model.Livro;

public class LivroBusiness implements Serializable {
	private static final long serialVersionUID = 4157130263688774470L;

	@Inject
	private LivroDAO livroDAO;

	public void gravar(Livro livro) {
		livro.setDataCadastro(LocalDateTime.now());
		livroDAO.gravar(livro);
	}

	public List<Livro> listar() {
		List<Livro> livros = livroDAO.listar();
		return livros;
	}
	
	public Livro listarPorId(Livro livro) {
		return livroDAO.listarPorId(livro.getId());
	}

	public void atualizar(Livro livro) {
		livroDAO.atualizar(livro);
	}

	public void apagar(Livro livro) {
		livroDAO.apagar(livro);
	}

}
