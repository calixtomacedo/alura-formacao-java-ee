package br.com.cmdev.javaejpa.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.cmdev.javaejpa.model.Conta;

public class TestandoEstados {
	
	public static void main(String[] args) {
		
		// Neste momento a conta esta no estado Transient
		Conta conta = new Conta();
		conta.setTitular("Izabel Macedo");
		conta.setAgencia(147);
		conta.setNumero(741852);
		conta.setSaldo(2000.00);
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("alura");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		// Transient --> para Managed
		entityManager.persist(conta);
		
		// Transient --> para Removed
		entityManager.remove(conta);
		
		entityManager.getTransaction().commit();
	}

}
