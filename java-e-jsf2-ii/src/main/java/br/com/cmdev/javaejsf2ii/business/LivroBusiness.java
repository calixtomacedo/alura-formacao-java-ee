package br.com.cmdev.javaejsf2ii.business;

import java.time.LocalDateTime;
import java.util.List;

import br.com.cmdev.javaejsf2ii.dao.LivroDAO;
import br.com.cmdev.javaejsf2ii.model.Livro;

public class LivroBusiness {

	public void gravar(Livro livro) throws Exception {
		livro.setDataCadastro(LocalDateTime.now());
		new LivroDAO().gravar(livro);
	}

	public List<Livro> listar() throws Exception {
		List<Livro> livros = new LivroDAO().listar();
		return livros;
	}

	public void atualizar(Livro livro) throws Exception {
		new LivroDAO().atualizar(livro);
	}
	
	public void apagar(Livro livro) throws Exception {
		new LivroDAO().apagar(livro);
	}


}
