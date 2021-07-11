package br.com.cmdev.javaeeparteiii.utils.infra;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ApplicationScoped
public class MailSender {

	@Resource(mappedName = "java:/jboss/mail/gmail")
	private Session session;
	
	public void send(String to, String subject, String messageBody) {
		MimeMessage message = new MimeMessage(session);
		try {
			message.setRecipients(RecipientType.TO, InternetAddress.parse(to));
			message.setFrom(new InternetAddress(session.getProperty("mail.smtp.user"), "CM-DEV Desenvolvimento"));
			message.setSubject(subject);
			message.setContent(messageBody, "text/html");

			Transport.send(message);
			
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
