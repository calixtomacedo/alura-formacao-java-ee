package br.com.cmdev.javaeeparteiii.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = LocalDate.class)
public class LocalDateConverter implements Converter<LocalDate> {

	@Override
	public LocalDate getAsObject(FacesContext context, UIComponent component, String dateText) {
		if (null == dateText || dateText.isEmpty()) {
			return null;
		}
		LocalDate localDate;
		try {
			localDate = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (DateTimeParseException e) {
			throw new ConverterException("O ano deve conter 4 dígitos. Exemplo: 01/07/2021.");
		}
		return localDate;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, LocalDate localDate) {
		if (null == localDate) {
			return "";
		}
		return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}
