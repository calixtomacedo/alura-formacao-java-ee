package br.com.cmdev.javaejsf.business;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import br.com.cmdev.javaejsf.dao.LivroDAO;
import br.com.cmdev.javaejsf.model.Livro;

public class LivroBusiness {

	public void gravar(Livro livro) {
		livro.setDataCadastro(LocalDateTime.now());
		new LivroDAO().gravar(livro);
	}

	public List<Livro> listar() {
		List<Livro> livros = new LivroDAO().listar();
		livros.sort(Comparator.comparing(Livro::getDataCadastro));
		return livros;
	}

	public void atualizar(Livro livro) {
		new LivroDAO().atualizar(livro);
	}
	
	public void apagar(Livro livro) {
		new LivroDAO().apagar(livro);
	}


}
