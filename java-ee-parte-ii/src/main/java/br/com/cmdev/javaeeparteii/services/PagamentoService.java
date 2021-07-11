package br.com.cmdev.javaeeparteii.services;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
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

import br.com.cmdev.javaeeparteii.dao.CompraDAO;
import br.com.cmdev.javaeeparteii.model.Compra;

@Path("/pagamento")
public class PagamentoService {

	@Context
	private HttpServletRequest context;
	
	@Inject
	private CompraDAO compraDAO;

	@Inject
	private PagamentoGateway pagamentoGateway;
	private static ExecutorService executor = Executors.newFixedThreadPool(50);

	@POST
	public void pagar(@Suspended final AsyncResponse asyncResponse, @QueryParam("uuid") String uuid) {
		String path = context.getContextPath();
		Compra compra = compraDAO.buscarPorUuId(uuid);
		executor.submit(() -> {
			try {
				pagamentoGateway.pagar(compra.getTotal());
				URI uri = UriBuilder.fromPath("http://localhost:8080".concat(path).concat("/home.xhtml")).build();
				Response response = Response.seeOther(uri).build();
				asyncResponse.resume(response);
			} catch (Exception e) {
				asyncResponse.resume(new WebApplicationException(e));
			}
		});
	}

	/*
	@POST
	public Response pagar(@QueryParam("uuid") String uuid) {
		Compra compra = compraDAO.buscarPorUuId(uuid);
		pagamentoGateway.pagar(compra.getTotal());
		URI uri = UriBuilder.fromPath("http://localhost:8080".concat(context.getContextPath()).concat("/home.xhtml")).queryParam("msg", "Compra realizado com sucesso").build();
		Response response = Response.seeOther(uri).build();
		return response;
	}
	*/

}
