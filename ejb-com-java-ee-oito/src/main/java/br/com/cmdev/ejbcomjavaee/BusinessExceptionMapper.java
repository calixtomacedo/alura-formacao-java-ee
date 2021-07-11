package br.com.cmdev.ejbcomjavaee;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.cmdev.ejbcomjavaee.dto.MensagensDeErroDTO;
import br.com.cmdev.ejbcomjavaee.exception.BusinessException;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

	@Override
	public Response toResponse(BusinessException exception) {
		return Response.status(Response.Status.BAD_REQUEST).entity(MensagensDeErroDTO.build(exception.getMessages())).build();
	}

}
