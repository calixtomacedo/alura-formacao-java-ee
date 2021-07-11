package br.com.cmdev.javaejpa.tests;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.cmdev.javaejpa.model.Conta;
import br.com.cmdev.javaejpa.model.Movimentacao;
import br.com.cmdev.javaejpa.util.TipoMovimentacao;

public class TestaMovimentacao {
	
	public static void main(String[] args) {
		
		Conta conta = new Conta();
		conta.setTitular("Maria da Gloria");
		conta.setAgencia(852);
		conta.setNumero(852369);
		conta.setSaldo(500.00);
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacao.setDescricao("Churrascaria");
		movimentacao.setValor(new BigDecimal(1210.00));
		movimentacao.setDataMovimentacao(LocalDateTime.now());
		movimentacao.setConta(conta);
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("alura");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(conta);
		entityManager.persist(movimentacao);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
