package br.com.cmdev.ejbcomjavaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cmdev.ejbcomjavaee.model.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void cadastrarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
		this.entityManager.persist(agendamentoEmail);
	}

	public List<AgendamentoEmail> listarAgendamentoEmail() {
		String jpql = "SELECT ag FROM AgendamentoEmail ag";
		return this.entityManager.createQuery(jpql, AgendamentoEmail.class).getResultList();
	}

	public List<AgendamentoEmail> listarAgendamentoEmail(String email) {
		String jpql = "SELECT ag FROM AgendamentoEmail ag WHERE ag.email = :pEmail AND ag.isEnviado = false";
		return this.entityManager.createQuery(jpql, AgendamentoEmail.class).setParameter("pEmail", email).getResultList();
	}

	public List<AgendamentoEmail> listarAgendamentoEmailNaoEnviado() {
		String jpql = "SELECT ag FROM AgendamentoEmail ag WHERE ag.isEnviado = false";
		return this.entityManager.createQuery(jpql, AgendamentoEmail.class).getResultList();
	}
	
	public void atualizarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
		this.entityManager.merge(agendamentoEmail);
	}
}
