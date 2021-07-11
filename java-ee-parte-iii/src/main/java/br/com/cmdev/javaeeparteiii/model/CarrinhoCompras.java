package br.com.cmdev.javaeeparteiii.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import br.com.cmdev.javaeeparteiii.dao.CompraDAO;

@Named
@SessionScoped
public class CarrinhoCompras implements Serializable {

	private static final long serialVersionUID = -6671322444353687808L;
	
	private Set<CarrinhoItem> itens = new HashSet<>();

	@Inject
	private CompraDAO compraDAO;
	
	@Inject
	private FacesContext context;
	
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

	public String finalizarCompra(Compra compra) {
		context.getExternalContext().getFlash().setKeepMessages(true);
		if(itens.isEmpty()){
			context.addMessage(null, new FacesMessage("Carrinho vazio. Favor adicionar ao menos um item ao carrinho."));
			return "/pages/home?faces-redirect=true";
		}
		compra.setItens(this.toJson());
		compra.setTotal(getTotal());
		compra.setDataCompra(LocalDateTime.now());
		compraDAO.salvar(compra);
		return "OK";
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
