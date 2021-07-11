package br.com.cmdev.javaeeparteii.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import br.com.cmdev.javaeeparteii.dao.CompraDAO;

@Named
@SessionScoped
public class CarrinhoCompras implements Serializable {

	private static final long serialVersionUID = -6671322444353687808L;
	
	private Set<CarrinhoItem> itens = new HashSet<>();

	@Inject
	private CompraDAO compraDAO;
	
	public void adiciona(CarrinhoItem item) {
		itens.add(item);
	}

	public List<CarrinhoItem> getItens() {
		return new ArrayList<CarrinhoItem>(itens);
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem carrinhoItem : itens) {
			total = total.add(carrinhoItem.getLivro().getPreco().multiply(new BigDecimal(carrinhoItem.getQuantidade())));
		}
		return total;
	}
	
	
	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getLivro().getPreco().multiply(new BigDecimal(item.getQuantidade()));
	}

	public void remover(CarrinhoItem item) {
		this.itens.remove(item);
	}
	
	public Integer getQuantidadeTotal() {
		return this.itens.stream().mapToInt(item -> item.getQuantidade()).sum();
	}

	public void finalizarCompra(Compra compra) {
		compra.setItens(this.toJson());
		compra.setTotal(getTotal());
		compraDAO.salvar(compra);
	}

	private String toJson() {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (CarrinhoItem item : itens) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			objectBuilder.add("titulo", item.getLivro().getTitulo());
			objectBuilder.add("preco", item.getLivro().getPreco());
			objectBuilder.add("quantidade", item.getQuantidade());
			objectBuilder.add("total", getTotal(item));
			builder.add(objectBuilder);
		}
		return builder.build().toString();
	}
	
}
