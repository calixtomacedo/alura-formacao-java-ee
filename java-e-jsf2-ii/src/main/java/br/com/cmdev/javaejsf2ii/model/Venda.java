package br.com.cmdev.javaejsf2ii.model;

public class Venda {

	private Livro livro; 
	private Integer quantidade;
	
	public Venda(Livro livro, Integer quantidade) {
		super();
		this.livro = livro;
		this.quantidade = quantidade;
	}
	
	public Livro getLivro() {
		return livro;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	
	
}
