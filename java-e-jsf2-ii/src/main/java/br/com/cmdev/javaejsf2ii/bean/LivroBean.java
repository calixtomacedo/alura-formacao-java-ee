package br.com.cmdev.javaejsf2ii.bean;

import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.primefaces.util.LangUtils;

import br.com.cmdev.javaejsf2ii.business.AutorBusiness;
import br.com.cmdev.javaejsf2ii.business.LivroBusiness;
import br.com.cmdev.javaejsf2ii.model.Autor;
import br.com.cmdev.javaejsf2ii.model.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private List<Livro> livroList;
	private List<Livro> livroFiltro;
	private Long autorId;
	

	public Livro getLivro() {
		return livro;
	}

	public List<Autor> getAutores() throws Exception {
		return new AutorBusiness().listar();
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
	
	public void adicionarAutor() throws Exception {
		Autor autor = new AutorBusiness().buscarAutor(this.autorId);
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

	public void gravar() throws Exception {
		LivroBusiness business = new LivroBusiness();
		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}
		if(this.livro.getId() == null) {
			business.gravar(livro);
			this.livroList = business.listar();
		}else {
			business.atualizar(livro);
		}
		this.livro = new Livro();
		this.autorId = null;
	}
	
	public List<Livro> listar() throws Exception{
		if(this.livroList == null) {
			this.livroList = new LivroBusiness().listar();
		}
		return livroList;
	}
	
	public void alterar(Livro livro) {
		this.livro = livro;
	}
	
	public void apagar(Livro livro) throws Exception {
		new LivroBusiness().apagar(livro);
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
