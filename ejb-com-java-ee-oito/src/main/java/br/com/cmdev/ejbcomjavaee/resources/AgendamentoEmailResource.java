package br.com.cmdev.ejbcomjavaee.resources;

import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cmdev.ejbcomjavaee.business.AgendamentoEmailBusiness;
import br.com.cmdev.ejbcomjavaee.exception.BusinessException;
import br.com.cmdev.ejbcomjavaee.model.AgendamentoEmail;

@Path("/agendamentoemail")
public class AgendamentoEmailResource {
	
	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarAgendamentoEmail(AgendamentoEmail agendamentoEmail) throws BusinessException {
		this.agendamentoEmailBusiness.cadastrarAgendamentoEmail(agendamentoEmail);
		
		return Response.status(Response.Status.CREATED).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarAgendamentoEmail() {
		List<AgendamentoEmail> listaAgendamentosEmail = agendamentoEmailBusiness.listarAgendamentosEmail();
		listaAgendamentosEmail.sort(Comparator.comparing(AgendamentoEmail::getId));
		return Response.ok(listaAgendamentosEmail).build();
	}

}
