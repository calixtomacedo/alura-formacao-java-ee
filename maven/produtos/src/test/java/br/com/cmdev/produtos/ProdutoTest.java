package br.com.cmdev.produtos;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProdutoTest {

	@Test
	public void verificaPrecoComImposto() {
		Produto tarmac = new Produto("Tarmac S-WORKS 2022", 100.000);
		assertEquals(110.00, tarmac.getPrecoComImposto(), 0.00001);
	}
	
	@Test
	public void verificaNomeDoProduto() {
		Produto tarmac = new Produto("Tarmac S-WORKS 2022", 100.000);
		assertEquals("Tarmac S-WORKS 2022", tarmac.getNome());
	}
	
	@Test
	public void verificaPrecoDoProduto() {
		Produto tarmac = new Produto("Tarmac S-WORKS 2022", 100.000);
		assertEquals(100.000, tarmac.getPreco(), 0.000001);
	}

}
