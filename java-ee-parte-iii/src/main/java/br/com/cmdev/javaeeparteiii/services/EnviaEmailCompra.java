package br.com.cmdev.javaeeparteiii.services;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.cmdev.javaeeparteiii.dao.CompraDAO;
import br.com.cmdev.javaeeparteiii.model.Compra;
import br.com.cmdev.javaeeparteiii.utils.infra.MailSender;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topics/CarrinhoComprasTopico"), @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic") })
public class EnviaEmailCompra implements MessageListener {

	@Inject
	private CompraDAO compraDAO;

	@Inject
	private MailSender mailSender;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			Compra compra;
			compra = compraDAO.buscarPorUuId(textMessage.getText());
			StringBuilder messageBody = new StringBuilder("Sua compra foi realizada com sucesso. Segue o numero do pedido " + compra.getUuid());
			String subject = "Nova Compra na Casa do Código";
			mailSender.send(compra.getUsuario().getEmail(), subject, messageBody.toString());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
