package br.com.cmdev.javaeeparteiii.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.QueryHints;

import br.com.cmdev.javaeeparteiii.model.Livro;

@Stateful
public class LivroDAO {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager manager;
	
	@Transactional
	public void salvar(Livro livro) {
		manager.persist(livro);
	}

	public List<Livro> listar() {
		String jpql = "SELECT DISTINCT(l) FROM Livro l JOIN FETCH l.autores ";
		return manager.createQuery(jpql, Livro.class).getResultList();
	}

	public Livro buscarPorId(Integer idLivro) {
		String jpql = "SELECT l FROM Livro l JOIN FETCH l.autores WHERE l.idLivro = :pIdLivro";
		return manager.createQuery(jpql, Livro.class).setParameter("pIdLivro", idLivro).getSingleResult();
	}

	public List<Livro> ultimosLancamentos() {
		String jpql = "SELECT l FROM Livro l ORDER BY l.idLivro DESC";
		return manager.createQuery(jpql, Livro.class)
				.setMaxResults(5)
				.setHint(QueryHints.HINT_CACHEABLE, true)
				.getResultList();
	}

	public List<Livro> demaisLivros() {
		String jpql = "SELECT l FROM Livro l ORDER BY l.idLivro DESC";
		return manager.createQuery(jpql, Livro.class)
				.setFirstResult(5)
				.setHint(QueryHints.HINT_CACHEABLE, true)
				.getResultList();
	}
	
	public void limpaCache() {
		Cache cache = manager.getEntityManagerFactory().getCache();
		
		//Exemplos
		cache.evict(Livro.class);
		//cache.evict(Livro.class, 1L);
		//cache.evictAll();
		
		SessionFactory factory = manager.getEntityManagerFactory().unwrap(SessionFactory.class);
		factory.getCache().evictAllRegions();
		factory.getCache().evictQueryRegion("home");
	}
}
