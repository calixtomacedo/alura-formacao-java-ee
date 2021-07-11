package br.com.cmdev.javaejpa.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.cmdev.javaejpa.model.Cliente;
import br.com.cmdev.javaejpa.model.Conta;

public class TestaRelacionamentoUmPraUm {

	public static void main(String[] args) {
		
		// Relacionando um Cliente para uma Conta
		Conta conta = new Conta();
		conta.setId(1L);

		Cliente cliente = new Cliente();
		cliente.setNome("Calixto Macedo");
		cliente.setEndereco("Paulo Cesar Ribeiro 1065");
		cliente.setProfissao("Programador Java");
		cliente.setConta(conta);
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("alura");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(cliente);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
