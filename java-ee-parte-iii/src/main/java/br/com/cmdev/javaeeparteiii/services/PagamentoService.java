package br.com.cmdev.javaeeparteiii.services;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.cmdev.javaeeparteiii.dao.CompraDAO;
import br.com.cmdev.javaeeparteiii.model.Compra;

@Path("/pagamento")
public class PagamentoService {

	@Context
	private HttpServletRequest context;
	
	@Inject
	private CompraDAO compraDAO;

	@Inject
	private PagamentoGateway pagamentoGateway;

	@Inject
	private JMSContext jmsContext;
	
	@Resource(name = "java:/jms/topics/CarrinhoComprasTopico")
	private Destination destination;
	
	private static ExecutorService executor = Executors.newFixedThreadPool(50);

	@POST
	public void pagar(@Suspended final AsyncResponse asyncResponse, @QueryParam("uuid") String uuid) {
		String contextPath = context.getContextPath();
		
		JMSProducer producer = jmsContext.createProducer();
		
		Compra compra = compraDAO.buscarPorUuId(uuid);
	
		executor.submit(() -> {
			try {
				pagamentoGateway.pagar(compra.getTotal());
				
				producer.send(destination, compra.getUuid());
				
				URI uri = UriBuilder.fromPath("http://192.168.0.18:8080".concat(contextPath).concat("/pages/home.xhtml")).build();
				Response response = Response.seeOther(uri).build();
				asyncResponse.resume(response);
				
			} catch (Exception e) {
				asyncResponse.resume(new WebApplicationException(e));
			}
		});
	}

}
