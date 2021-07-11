package br.com.cmdev.ejbcomjakartaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cmdev.ejbcomjakartaee.model.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void cadastarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
		this.entityManager.persist(agendamentoEmail);
	}

	public List<AgendamentoEmail> listarAgendamentoEmail() {
		String jpql = "SELECT ae FROM AgendamentoEmail ae";
		return this.entityManager.createQuery(jpql, AgendamentoEmail.class).getResultList();
	}
	
	public List<AgendamentoEmail> listarAgendamentoEmailNaoEnviado() {
		String jpql = "SELECT ae FROM AgendamentoEmail ae WHERE ae.isEnviado = false";
		return this.entityManager.createQuery(jpql, AgendamentoEmail.class).getResultList();
	}
	
	public void alterarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
		this.entityManager.merge(agendamentoEmail);
	}
}
