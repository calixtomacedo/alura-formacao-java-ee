package br.com.cmdev.javaeeparteiii.utils.serializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
	
	public LocalDateTime unmarshal(String localDateTime) throws Exception {
		if(localDateTime == null || localDateTime.isBlank()) {
			return null;
		}
		return LocalDateTime.parse(localDateTime);
	}

	public String marshal(LocalDateTime localDateTime) throws Exception {
		if(localDateTime == null) {
			return "";
		}
		return localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}
}