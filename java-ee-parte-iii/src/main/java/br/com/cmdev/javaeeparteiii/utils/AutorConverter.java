package br.com.cmdev.javaeeparteiii.utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.cmdev.javaeeparteiii.model.Autor;

@FacesConverter("autorConverter")
public class AutorConverter implements Converter<Autor> {

	@Override
	public Autor getAsObject(FacesContext context, UIComponent component, String autoId) {
		if(autoId == null || autoId.isBlank()) {
			return null;
		}
		Autor autor = new Autor();
		autor.setIdAutor(Integer.valueOf(autoId));
		return autor;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Autor autor) {
		if(autor == null) {
			return "";
		}
		return autor.getIdAutor().toString();
	}

}
