package br.com.cmdev.javaejsf2iii.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_LIVROS")
public class Livro implements Serializable {
	private static final long serialVersionUID = -794714917570466128L;

	@Id
	@Column(name = "IDLIVRO")
	@SequenceGenerator(name = "TB_LIVROS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_LIVROS_SEQ")
	private Long id;
	private String titulo;
	private String isbn;
	private BigDecimal preco;
	private LocalDate dataLancamento;
	private LocalDateTime dataCadastro;

	//@ManyToMany(fetch = FetchType.EAGER) //todo relacionamento que tenha o ToMany da direita usa o carregamento Lazy // Evitar o uso, pois não é uma boa pratica. Podemos sobre carregar o banco de dados
	@ManyToMany
	@JoinTable(name = "TB_LIVROSAUTORES", joinColumns = @JoinColumn(name = "IDLIVRO"), inverseJoinColumns = @JoinColumn(name = "IDAUTOR"))
	private List<Autor> autores = new ArrayList<Autor>();

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(Autor autor) {
		this.autores.add(autor);
	}

	public void remove(Autor autor) {
		this.autores.remove(autor);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


}