package br.com.cmdev.javaeeparteii.services;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import br.com.cmdev.javaeeparteii.model.Pagamento;

public class PagamentoGateway implements Serializable {
	
	private static final long serialVersionUID = -5086475355142682395L;

	public Response pagar(BigDecimal value) {

		ClientBuilder client = ClientBuilder.newBuilder();
		String urlTarget = "http://book-payment.herokuapp.com/payment";
		Entity<Pagamento> json = Entity.json(new Pagamento(value));
		return client.build().target(urlTarget).request().post(json);
	}

}
