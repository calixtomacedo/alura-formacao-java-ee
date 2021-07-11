package br.com.cmdev.ejbcomjavaee.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MessageListener;

import br.com.cmdev.ejbcomjavaee.business.AgendamentoEmailBusiness;
import br.com.cmdev.ejbcomjavaee.interception.AluraLogger;
import br.com.cmdev.ejbcomjavaee.model.AgendamentoEmail;

@AluraLogger
@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/EmailQueue"), @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class EmailMDB implements MessageListener {

	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;
	
	@Override
	public void onMessage(javax.jms.Message message) {

		try {
			AgendamentoEmail agendamentoMessage = message.getBody(AgendamentoEmail.class);
			this.agendamentoEmailBusiness.enviarEmail(agendamentoMessage);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}