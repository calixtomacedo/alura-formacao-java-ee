package br.com.cmdev.javaejsf2iii.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.util.LangUtils;

import br.com.cmdev.javaejsf2iii.business.AutorBusiness;
import br.com.cmdev.javaejsf2iii.business.LivroBusiness;
import br.com.cmdev.javaejsf2iii.model.Autor;
import br.com.cmdev.javaejsf2iii.model.Livro;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 6133705391291757993L;
	
	@Inject
	private LivroBusiness livroBusiness;
	
	@Inject
	private AutorBusiness autorBusiness;
	
	@Inject
	private FacesContext context;
	
	private Livro livro = new Livro();
	private List<Livro> livroList;
	private List<Livro> livroFiltro;
	private Long autorId;
	

	public Livro getLivro() {
		return livro;
	}

	public List<Autor> getAutores() {
		return autorBusiness.listar();
	}

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public String formAutor() {
		return "autor?faces-redirect=true";
	}
	
	public void adicionarAutor() {
		Autor autor = autorBusiness.buscarAutor(this.autorId);
		this.livro.setAutores(autor);
	}
	
	public void removeAutor(Autor autor) {
		this.livro.remove(autor);
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}
	
	public List<Livro> getLivroFiltro() {
		return livroFiltro;
	}

	public void setLivroFiltro(List<Livro> livroFiltro) {
		this.livroFiltro = livroFiltro;
	}

	public void validarIsbn(FacesContext fc, UIComponent component, Object object) throws ValidatorException {
		if(!object.toString().contains("1")) {
			throw new ValidatorException(new FacesMessage("O campo ISBN aceita apenas numérico."));
		}
	}

	public void gravar() {
		if (livro.getAutores().isEmpty()) {
			context.addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}
		if(this.livro.getId() == null) {
			livroBusiness.gravar(livro);
		}else {
			livroBusiness.atualizar(livro);
		}
		this.livroList = livroBusiness.listar();
		this.livro = new Livro();
		this.autorId = null;
	}
	
	public List<Livro> listar(){
		if(this.livroList == null) {
			this.livroList = livroBusiness.listar();
		}
		return livroList;
	}
	
	public void alterar(Livro livro) {
		this.livro = livroBusiness.listarPorId(livro);
	}
	
	public void apagar(Livro livro) {
		livroBusiness.apagar(livro);
		this.livroList = livroBusiness.listar();
	}
	
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        Livro livro = (Livro) value;
        return livro.getTitulo().toLowerCase().contains(filterText);
    }
}
