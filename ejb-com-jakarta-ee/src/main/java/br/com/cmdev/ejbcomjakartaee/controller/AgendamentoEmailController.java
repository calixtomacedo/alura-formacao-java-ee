package br.com.cmdev.ejbcomjakartaee.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cmdev.ejbcomjakartaee.model.AgendamentoEmail;
import br.com.cmdev.ejbcomjakartaee.services.AgendamentoEmailService;

@Path("emails")
public class AgendamentoEmailController {

	@Inject
	private AgendamentoEmailService service; 
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response  cadastarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
		service.cadastarAgendamentoEmail(agendamentoEmail);
		return Response.status(Response.Status.CREATED).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarAgendamentoEmail() {
		return Response.ok(this.service.listarAgendamentoEmail()).build();
	}
}
