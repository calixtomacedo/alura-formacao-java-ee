package br.com.cmdev.javaejpa.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.cmdev.javaejpa.model.Conta;

public class CriaContaComSaldo {

	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setTitular("Rosimeire Santos");
		conta.setAgencia(222);
		conta.setNumero(654321);
		conta.setSaldo(1000.00);
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("alura");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(conta);
		entityManager.getTransaction().commit();
	}
}
