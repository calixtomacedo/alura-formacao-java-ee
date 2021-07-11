package br.com.cmdev.ejbcomjavaee.exception;

import java.util.ArrayList;
import java.util.List;

//@ApplicationException(rollback = true)
public class BusinessException extends Exception {

	private static final long serialVersionUID = -6513630818748576014L;

	private List<String> messages;

	public BusinessException() {
		super();
		messages = new ArrayList<String>();
	}

	public BusinessException(String message) {
		super(message);
		messages = new ArrayList<String>();
		messages.add(message);
	}

	public List<String> getMessages() {
		return messages;
	}

	public void addMessage(String message) {
		this.messages.add(message);
	}

}
