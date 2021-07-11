package br.com.cmdev.javaejsf2iii.util.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import br.com.cmdev.javaejsf2iii.util.annotations.Transactional;

@Transactional
@Interceptor
public class TransactionsManager implements Serializable {

	private static final long serialVersionUID = -4207356795482713995L;
	
	@Inject
	private EntityManager entityManager;
	
	@AroundInvoke
	public Object executeTransaction(InvocationContext context) throws Exception {
		
		// Cria a transação
		entityManager.getTransaction().begin();
		
		// Executa os métodos que precisam de transação
		Object proceed = context.proceed();
		
		// Commita a transação
		entityManager.getTransaction().commit();
		
		return proceed;
	}
	

}
