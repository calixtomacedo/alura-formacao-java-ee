package br.com.cmdev.javaejsf2iii.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_VENDAS")
public class Venda implements Serializable {
	private static final long serialVersionUID = 565478606286290274L;
	
	@Id
	@Column(name = "IDVENDA")
	private Long id; 
	private Integer quantidade;
	public LocalDateTime dataVenda;
	public LocalDateTime dataAlteracao;
	
	@ManyToOne
	@JoinColumn(name = "IDLIVRO")
	private Livro livro;
	
	public Livro getLivro() {
		return livro;
	}
	public Integer getQuantidade() {
		return quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
