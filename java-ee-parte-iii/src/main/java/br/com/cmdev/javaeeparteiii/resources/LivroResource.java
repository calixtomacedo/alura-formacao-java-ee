package br.com.cmdev.javaeeparteiii.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import br.com.cmdev.javaeeparteiii.dao.LivroDAO;
import br.com.cmdev.javaeeparteiii.model.Livro;

@Path("livros")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class LivroResource {

	@Inject
	private LivroDAO livroDAO;
	
	@GET
	@Path("lancamentos")
	@Wrapped(element = "livros")
	public List<Livro> ultimosLancamentosJson() {
		return livroDAO.ultimosLancamentos();
	}
	
}
