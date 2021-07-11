package br.com.cmdev.maven.lojaweb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cmdev.produtos.Produto;



@WebServlet(urlPatterns={"/contato"})
public class ContatoServlet extends HttpServlet {
	private static final long serialVersionUID = -4641772472349197653L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Produto produto = new Produto("Bola", 10.00);
		
		PrintWriter writer = resp.getWriter();
		writer.println("<html><h2>Entre em Contado "+produto.getNome() +" preço: "+produto.getPreco()+"</h2></html>");
		writer.close();
	}

}
