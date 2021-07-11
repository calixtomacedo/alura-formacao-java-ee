package br.com.cmdev.javaejpa.tests;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.cmdev.javaejpa.model.Categoria;
import br.com.cmdev.javaejpa.model.Conta;
import br.com.cmdev.javaejpa.model.Movimentacao;
import br.com.cmdev.javaejpa.util.TipoMovimentacao;

public class TestaRelacionamentoMuitosParaMuitos {

	public static void main(String[] args) {
		
		Conta conta = new Conta();
		conta.setId(2L);

		Categoria categoria = new Categoria("Viagem Passeio");
		Categoria categoriaDois = new Categoria("Negócio Negócio");
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setDescricao("Viajem à Minas Gerais");
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacao.setDataMovimentacao(LocalDateTime.now());
		movimentacao.setValor(new BigDecimal(800.00));
		movimentacao.setCategorias(Arrays.asList(categoria, categoriaDois));
		movimentacao.setConta(conta);
		
		Movimentacao movimentacaoDois = new Movimentacao();
		movimentacaoDois.setDescricao("Viajem à São Paulo");
		movimentacaoDois.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacaoDois.setDataMovimentacao(LocalDateTime.now());
		movimentacaoDois.setValor(new BigDecimal(1200.00));
		movimentacaoDois.setCategorias(Arrays.asList(categoria, categoriaDois));
		movimentacaoDois.setConta(conta);
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("alura");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(categoria);
		entityManager.persist(categoriaDois);
		entityManager.persist(movimentacao);
		entityManager.persist(movimentacaoDois);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
