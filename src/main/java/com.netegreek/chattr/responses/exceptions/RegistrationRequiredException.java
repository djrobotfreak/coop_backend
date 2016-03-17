package com.netegreek.chattr.responses.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import io.dropwizard.jersey.errors.ErrorMessage;

public class RegistrationRequiredException extends WebApplicationException {

	public RegistrationRequiredException() {

		super(Response.status(307).entity(
				new ErrorMessage(307, "Authentication successful but user not found, " +
						"registration with app required")).build());
	}

}
