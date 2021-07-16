package br.com.cmdev.javaeeparteiii.utils.security;

import java.security.MessageDigest;
import java.util.Base64;

public class EncryptFactory {
	
	
	public static void main(String[] args) {
		EncryptFactory e = new EncryptFactory();
		String password = e.encryptPassword("654321");
		System.out.println(password);
	}

	
	public String encryptPassword(String password) {
		try {
			byte[] digest = MessageDigest.getInstance("sha-256").digest(password.getBytes());
			return Base64.getEncoder().encodeToString(digest);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
