package br.com.cmdev.javaejpa.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.cmdev.javaejpa.model.Conta;

public class UpdateSaldo {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("alura");
		EntityManager entityManager = factory.createEntityManager();
		Conta conta = entityManager.find(Conta.class, 1L);
		System.out.println("Titular: "+conta.getTitular()+ ", Agencia/Conta: "+ conta.getAgencia()+"-"+conta.getNumero()+", Saldo: "+conta.getSaldo());
		
		entityManager.getTransaction().begin();
		conta.setSaldo(2400.00);
		entityManager.getTransaction().commit();
		
		System.out.println("Titular: "+conta.getTitular()+ ", Agencia/Conta: "+ conta.getAgencia()+"-"+conta.getNumero()+", Saldo: "+conta.getSaldo());
	}
}
