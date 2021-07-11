package br.com.cmdev.javaejpa.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.cmdev.javaejpa.model.Conta;
import br.com.cmdev.javaejpa.model.Movimentacao;

public class TestaJPQL {

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("alura");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Conta conta = new Conta();
		conta.setId(1L);
		
		// JPQL = Java Persistence Query language
		//String jpql = "SELECT m FROM Movimentacao m WHERE m.conta.id = 1";
		//String jpql = "SELECT m FROM Movimentacao m";
		String jpql = "SELECT m FROM Movimentacao m WHERE m.conta = :pConta order by m.valor desc";
		
		//Query query = entityManager.createQuery(jpql);
		TypedQuery<Movimentacao> query = entityManager.createQuery(jpql, Movimentacao.class);
		query.setParameter("pConta", conta);
		
		List<Movimentacao> movimentacaoList = query.getResultList();
		
		movimentacaoList.forEach(m -> {
			System.out.println("Id: "+m.getId());
			System.out.println("Descricao: "+m.getDescricao());
			System.out.println("Valor: "+m.getValor());
			System.out.println("Tipo: "+m.getTipoMovimentacao());
		});
	}
}
