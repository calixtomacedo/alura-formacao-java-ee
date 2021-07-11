package br.com.cmdev.javaejpa.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.cmdev.javaejpa.model.Conta;

public class CriaConta {

	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setTitular("Calixto Macedo");
		conta.setAgencia(111);
		conta.setNumero(123456);
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("alura-mysql");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(conta);
		entityManager.getTransaction().commit();
	}
}
