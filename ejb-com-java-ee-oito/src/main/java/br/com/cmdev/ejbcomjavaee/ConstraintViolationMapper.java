package br.com.cmdev.ejbcomjavaee;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.cmdev.ejbcomjavaee.dto.MensagensDeErroDTO;

@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		Stream<String> streamMap = exception.getConstraintViolations().stream().map(constraint -> constraint.getMessage());
		List<String> stringList = streamMap.collect(Collectors.toList());
		return Response.status(Response.Status.BAD_REQUEST).entity(MensagensDeErroDTO.build(stringList)).build();
	}

}
