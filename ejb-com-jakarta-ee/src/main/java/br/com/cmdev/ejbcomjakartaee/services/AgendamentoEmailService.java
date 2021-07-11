package br.com.cmdev.ejbcomjakartaee.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.cmdev.ejbcomjakartaee.dao.AgendamentoEmailDAO;
import br.com.cmdev.ejbcomjakartaee.model.AgendamentoEmail;

@Stateless
public class AgendamentoEmailService {

	@Inject
	private AgendamentoEmailDAO dao;
	
	@Inject
	SendEmailsService emailsService;

	private static final Logger LOGGER = Logger.getLogger(AgendamentoEmailService.class.getName());

	public void cadastarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setIsEnviado(Boolean.FALSE);
		agendamentoEmail.setDataAgendamento(LocalDateTime.now());
		dao.cadastarAgendamentoEmail(agendamentoEmail);
	}

	public List<AgendamentoEmail> listarAgendamentoEmail() {
		return dao.listarAgendamentoEmail();
	}

	public List<AgendamentoEmail> listarAgendamentoEmailNaoEnviado() {
		return dao.listarAgendamentoEmailNaoEnviado();
	}

	public void alterarAgendamentoEmailParaEnviado(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setIsEnviado(Boolean.TRUE);
		dao.cadastarAgendamentoEmail(agendamentoEmail);
	}

	public void enviarEmail(AgendamentoEmail agendamentoEmail) {
		try {
			this.emailsService.send(agendamentoEmail.getEmail(), agendamentoEmail.getAssunto(), agendamentoEmail.getMensagem());
		} catch (Exception e) {
			LOGGER.warning(e.getMessage());
		}
	}

}
