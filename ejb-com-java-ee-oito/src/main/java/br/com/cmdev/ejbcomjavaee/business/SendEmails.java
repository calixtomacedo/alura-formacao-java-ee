package br.com.cmdev.ejbcomjavaee.business;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class SendEmails {

	@Resource(lookup = "java:/jboss/mail/gmail")
	private Session sessionMail;

	public SendEmails() {
	}
	
	public void send(String to, String subject, String body) {
		MimeMessage mimeMessage = new MimeMessage(sessionMail);
		try {
			mimeMessage.setFrom(new InternetAddress(sessionMail.getProperty("mail.smtp.user"), "Email Automatico - CM-DEV"));
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(Optional.ofNullable(body).orElse(""));
			Transport.send(mimeMessage);
		} catch (MessagingException ex) {
			throw new RuntimeException(ex);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
