package br.com.cmdev.javaejpa.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.cmdev.javaejpa.model.Conta;

public class EstadoDetached {

	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setTitular("Geraldo Macedo");
		conta.setAgencia(333);
		conta.setNumero(456789);
		conta.setSaldo(100.00);
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("alura");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(conta);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		
		System.out.println("Conta vindo ja da Base: "+conta.getId()+", "+conta.getTitular()+", "+conta.getAgencia()+", "+conta.getNumero()+", "+conta.getSaldo());
		
		EntityManager entityManagerDois = entityManagerFactory.createEntityManager();
		conta.setSaldo(5000.00);
		
		entityManagerDois.getTransaction().begin();
		entityManagerDois.merge(conta);
		entityManagerDois.getTransaction().commit();
	}
}
