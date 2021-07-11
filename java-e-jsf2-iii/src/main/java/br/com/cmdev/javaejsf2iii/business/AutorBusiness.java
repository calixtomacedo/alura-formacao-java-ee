package br.com.cmdev.javaejsf2iii.business;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import br.com.cmdev.javaejsf2iii.dao.AutorDAO;
import br.com.cmdev.javaejsf2iii.model.Autor;

public class AutorBusiness implements Serializable {

	private static final long serialVersionUID = -5394406130846350591L;
	
	@Inject
	private AutorDAO autorDAO;

	public void gravar(Autor autor) {
		autor.setDataCadastro(LocalDateTime.now());
		autorDAO.gravar(autor);
	}

	public List<Autor> listar() {
		return autorDAO.listar();
	}

	public Autor buscarAutor(Long autorId) {
		return autorDAO.buscarAutor(autorId);
	}

	public void alterar(Autor autor) {
		autorDAO.alterar(autor);
	}

	public void apagar(Autor autor) {
		autorDAO.apagar(autor);
	}

}
