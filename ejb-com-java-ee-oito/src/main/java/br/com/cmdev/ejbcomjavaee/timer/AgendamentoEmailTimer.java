package br.com.cmdev.ejbcomjavaee.timer;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.cmdev.ejbcomjavaee.business.AgendamentoEmailBusiness;
import br.com.cmdev.ejbcomjavaee.model.AgendamentoEmail;

@Singleton
public class AgendamentoEmailTimer {

	private static Logger log = Logger.getLogger(AgendamentoEmailTimer.class.getName());

	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;

	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;

	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;

	@Schedule(dayOfWeek = "Fri", hour = "0", minute = "0")
	public void enviarEmailAgendados() {

		log.info("Preparando para enviar os email agendados: ");

		List<AgendamentoEmail> emailNaoEnviadoList = this.agendamentoEmailBusiness.listarAgendamentoEmailNaoEnviado();

		emailNaoEnviadoList.stream().forEach(ag -> log.info(ag.getEmail()));

		emailNaoEnviadoList.stream().forEach(agendamento -> {
			context.createProducer().send(queue, agendamento);
			this.agendamentoEmailBusiness.definirAgendamentoEmailComoEnviado(agendamento);
		});

		log.info("Finalizado o envio dos email agendados!");
	}

}
