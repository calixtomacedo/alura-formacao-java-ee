package br.com.cmdev.produtos;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {

	@Test
	public void shouldAnswerWithTrue() {
		App.main(new String[] {"A", "B", "C"});
		assertTrue(true);
	}
}
