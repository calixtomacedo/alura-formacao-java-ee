package br.com.cmdev.javaejsf.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("localDateFacesConverter")
public class LocalDateFacesConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String stringValue) {
		if (null == stringValue || stringValue.isEmpty()) {
			return null;
		}
		LocalDate localDate;
		try {
			localDate = LocalDate.parse(stringValue, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (DateTimeParseException e) {
			throw new ConverterException("O ano deve conter 4 dígitos. Exemplo: 01/01/2021.");
		}
		return localDate;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object localDateValue) {
		if (null == localDateValue) {
			return "";
		}
		return ((LocalDate) localDateValue).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
}