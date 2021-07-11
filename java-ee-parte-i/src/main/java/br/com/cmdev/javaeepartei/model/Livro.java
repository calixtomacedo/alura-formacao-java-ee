package br.com.cmdev.javaeepartei.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

/**
 * @author calixto.macedo - Calixto Macedo
 *
 */
/**
 * @author calixto.macedo - Calixto Macedo
 *
 */
/**
 * @author calixto.macedo - Calixto Macedo
 *
 */
/**
 * @author calixto.macedo - Calixto Macedo
 *
 */
@Entity()
@Table(name = "TB_LIVROS")
public class Livro implements Serializable {
	private static final long serialVersionUID = -7891622235098185796L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLivro;
	
	@NotBlank
	private String titulo;
	
	@Lob
	@Length(min = 10)
	@NotBlank
	private String descricao;
	
	@DecimalMin("20")
	private BigDecimal preco;

	@Min(50)
	private Integer numeroPaginas;
	
	private LocalDate dataLancamento;
	
	private LocalDateTime dataCadastro;
	
	 private String capaPath;

	@ManyToMany()
	@JoinTable(name = "TB_LIVROSAUTORES", joinColumns = @JoinColumn(name = "idLivro", referencedColumnName = "idLivro"), inverseJoinColumns = @JoinColumn(name = "idAutor", referencedColumnName = "idAutor"))
	@Size(min = 1) 
	@NotNull
	private List<Autor> autores = new ArrayList<Autor>();

	public Integer getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
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
	
	public String getCapaPath() {
		return capaPath;
	}

	public void setCapaPath(String capaPath) {
		this.capaPath = capaPath;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	@Override
	public String toString() {
		return "Livro [idLivro=" + idLivro + ", titulo=" + titulo + ", descricao=" + descricao + ", preco=" + preco + ", numeroPaginas=" + numeroPaginas + ", dataLancamento=" + dataLancamento + ", dataCadastro=" + dataCadastro + ", autores=" + autores + "]";
	}

}
