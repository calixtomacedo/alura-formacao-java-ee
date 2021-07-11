package br.com.cmdev.javaejsf.business;

import java.time.LocalDateTime;
import java.util.List;

import br.com.cmdev.javaejsf.dao.AutorDAO;
import br.com.cmdev.javaejsf.model.Autor;

public class AutorBusiness {

	public void gravar(Autor autor) {
		autor.setDataCadastro(LocalDateTime.now());
		new AutorDAO().gravar(autor);
	}

	public List<Autor> listar() {
		return new AutorDAO().listar();
	}

	public Autor buscarAutor(Long autorId) {
		return new AutorDAO().buscarAutor(autorId);
	}

	public void alterar(Autor autor) {
		new AutorDAO().alterar(autor);
	}

	public void apagar(Autor autor) {
		new AutorDAO().apagar(autor);
	}

}
