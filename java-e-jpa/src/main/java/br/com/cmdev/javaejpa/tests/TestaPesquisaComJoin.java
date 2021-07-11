package br.com.cmdev.javaejpa.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.cmdev.javaejpa.model.Categoria;
import br.com.cmdev.javaejpa.model.Movimentacao;
import br.com.cmdev.javaejpa.util.TipoMovimentacao;

public class TestaPesquisaComJoin {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("alura");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Categoria categoria = new Categoria();
		categoria.setId(3L);
		
		String jpql = "SELECT m FROM Movimentacao m JOIN m.categorias c WHERE c = :pCategoria AND m.valor > 800 AND m.tipoMovimentacao = :pTipoMovimentacao"; 
		TypedQuery<Movimentacao> query = entityManager.createQuery(jpql, Movimentacao.class);

		query.setParameter("pCategoria", categoria);
		query.setParameter("pTipoMovimentacao", TipoMovimentacao.SAIDA);

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
