package br.com.cmdev.ejbcomjavaee.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;

import br.com.cmdev.ejbcomjavaee.dao.AgendamentoEmailDAO;
import br.com.cmdev.ejbcomjavaee.exception.BusinessException;
import br.com.cmdev.ejbcomjavaee.interception.AluraLogger;
import br.com.cmdev.ejbcomjavaee.model.AgendamentoEmail;

@Stateless
@AluraLogger
//@TransactionManagement(TransactionManagementType.CONTAINER)
public class AgendamentoEmailBusiness {

	@Inject
	private AgendamentoEmailDAO agendamentoEmailDAO;
	
	@Inject
	private SendEmails email;

	//@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastrarAgendamentoEmail(@Valid AgendamentoEmail agendamentoEmail) throws BusinessException {
		if (agendamentoEmailDAO.listarAgendamentoEmail(agendamentoEmail.getEmail()).isEmpty()) {
			agendamentoEmail.setIsEnviado(Boolean.FALSE);
			// agendamentoEmail.setDataAgendamento(LocalDateTime.now());
			this.agendamentoEmailDAO.cadastrarAgendamentoEmail(agendamentoEmail);
		} else {
			throw new BusinessException("O email: ".concat(agendamentoEmail.getEmail()).concat(", possue um agendamento ativo!"));
		}
		throw new BusinessException("Não posso gravar ");
	}

	public List<AgendamentoEmail> listarAgendamentosEmail() {
		return this.agendamentoEmailDAO.listarAgendamentoEmail();
	}

	public List<AgendamentoEmail> listarAgendamentoEmailNaoEnviado() {
		return this.agendamentoEmailDAO.listarAgendamentoEmailNaoEnviado();
	}

	public void enviarEmail(AgendamentoEmail agendamentoEmail) {
		email.send(agendamentoEmail.getEmail(), agendamentoEmail.getAssunto(), agendamentoEmail.getMensagem());
	}

	public void definirAgendamentoEmailComoEnviado(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setIsEnviado(Boolean.TRUE);
		this.agendamentoEmailDAO.atualizarAgendamentoEmail(agendamentoEmail);
	}

}
