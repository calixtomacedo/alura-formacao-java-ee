package br.com.cmdev.javaeeparteiii.bean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.cmdev.javaeeparteiii.dao.LivroDAO;
import br.com.cmdev.javaeeparteiii.model.CarrinhoCompras;
import br.com.cmdev.javaeeparteiii.model.CarrinhoItem;
import br.com.cmdev.javaeeparteiii.model.Livro;

@Named(value = "carrinho")
public class CarrinhoComprasBean {
	
	@Inject
	private LivroDAO livroDAO;

	@Inject
	private CarrinhoCompras carrinho;
	
	public String adicionaNoCarrinhoDeCompras(Integer idLivro) {
		
		Livro livro = livroDAO.buscarPorId(idLivro);
		CarrinhoItem item = new CarrinhoItem(livro);
		carrinho.adiciona(item);
		
		return "carrinho?faces-redirect=true";
	}
	
	public List<CarrinhoItem> getItens(){
		return carrinho.getItens();
	}
	
	
	public void remover(CarrinhoItem item) {
		carrinho.remover(item);
	}
	
}
