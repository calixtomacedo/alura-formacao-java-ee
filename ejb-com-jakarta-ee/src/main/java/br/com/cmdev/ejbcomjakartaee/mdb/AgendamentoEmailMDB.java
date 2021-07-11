package br.com.cmdev.ejbcomjakartaee.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.com.cmdev.ejbcomjakartaee.model.AgendamentoEmail;
import br.com.cmdev.ejbcomjakartaee.services.AgendamentoEmailService;

@MessageDriven(activationConfig = { 
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/EmailQueue"), 
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class AgendamentoEmailMDB implements MessageListener {

	@Inject
	private AgendamentoEmailService agendamentoEmailService;

	@Override
	public void onMessage(Message message) {
		try {
			AgendamentoEmail agendamentoEmail = message.getBody(AgendamentoEmail.class);
			this.agendamentoEmailService.enviarEmail(agendamentoEmail);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
	
}
