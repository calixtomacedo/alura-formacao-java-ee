package br.com.cmdev.javaejsf2ii.business;

import java.time.LocalDateTime;
import java.util.List;

import br.com.cmdev.javaejsf2ii.dao.AutorDAO;
import br.com.cmdev.javaejsf2ii.model.Autor;

public class AutorBusiness {

	public void gravar(Autor autor) throws Exception {
		autor.setDataCadastro(LocalDateTime.now());
		new AutorDAO().gravar(autor);
	}

	public List<Autor> listar() throws Exception {
		return new AutorDAO().listar();
	}

	public Autor buscarAutor(Long autorId) throws Exception {
		return new AutorDAO().buscarAutor(autorId);
	}

	public void alterar(Autor autor) throws Exception {
		new AutorDAO().alterar(autor);
	}

	public void apagar(Autor autor) throws Exception {
		new AutorDAO().apagar(autor);
	}

}
