package br.com.cmdev.javaeeparteiii.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = LocalDateTime.class) 
public class LocalDateTimeConverter implements Converter<LocalDateTime> {

	@Override
	public LocalDateTime getAsObject(FacesContext context, UIComponent component, String dateTimeTex) {
		if (dateTimeTex == null || dateTimeTex.isBlank()) {
			return null;
		}
		LocalDateTime localDateTime;
		localDateTime = LocalDateTime.parse(dateTimeTex, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		return localDateTime;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, LocalDateTime dateTime) {
		if(dateTime == null) {
			return "";
		}
		return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}

}
