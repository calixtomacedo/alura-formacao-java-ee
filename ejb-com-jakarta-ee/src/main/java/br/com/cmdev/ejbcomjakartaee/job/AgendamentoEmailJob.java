package br.com.cmdev.ejbcomjakartaee.job;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.cmdev.ejbcomjakartaee.model.AgendamentoEmail;
import br.com.cmdev.ejbcomjakartaee.services.AgendamentoEmailService;

@Singleton
public class AgendamentoEmailJob {

	@Inject
	private AgendamentoEmailService agendamentoEmailService;
	
	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;
	
	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;

	@Schedule(hour = "*", minute = "*", second = "*/30")
	public void enviarEmail() {

		List<AgendamentoEmail> agendamentoEmailList = this.agendamentoEmailService.listarAgendamentoEmailNaoEnviado();

		agendamentoEmailList.forEach(agendamentoEmail -> {
			this.context.createProducer().send(queue, agendamentoEmail);
			this.agendamentoEmailService.alterarAgendamentoEmailParaEnviado(agendamentoEmail);
		});

	}
}
