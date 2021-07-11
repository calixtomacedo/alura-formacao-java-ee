package br.com.cmdev.javaeeparteiii.utils.serializer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
	
	public LocalDate unmarshal(String localDate) throws Exception {
		if(localDate == null || localDate.isBlank()) {
			return null;
		}
		return LocalDate.parse(localDate);
	}

	public String marshal(LocalDate localDate) throws Exception {
		if(localDate == null) {
			return "";
		}
		return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
}