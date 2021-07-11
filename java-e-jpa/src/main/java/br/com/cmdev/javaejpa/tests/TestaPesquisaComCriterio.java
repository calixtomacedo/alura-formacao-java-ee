package br.com.cmdev.javaejpa.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.cmdev.javaejpa.model.Categoria;
import br.com.cmdev.javaejpa.model.Movimentacao;

public class TestaPesquisaComCriterio {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("alura");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Categoria categoria = new Categoria();
		categoria.setId(1L);

		// JPQL = Java Persistence Query language
		// String jpql = "SELECT m FROM Movimentacao m WHERE m.conta.id = 1";
		// String jpql = "SELECT m FROM Movimentacao m";
		String jpql = "SELECT m FROM Movimentacao m JOIN m.categorias c WHERE c = :pCategoria";

		// Query query = entityManager.createQuery(jpql);
		TypedQuery<Movimentacao> query = entityManager.createQuery(jpql, Movimentacao.class);
		query.setParameter("pCategoria", categoria);

		List<Movimentacao> movimentacaoList = query.getResultList();

		movimentacaoList.forEach(m -> {
			System.out.println("Id: " + m.getId());
			System.out.println("Descricao: " + m.getDescricao());
			System.out.println("Valor: " + m.getValor());
			System.out.println("Tipo: " + m.getTipoMovimentacao());
			System.out.println("Tipo: " + m.getCategorias());
		});
	}
}
