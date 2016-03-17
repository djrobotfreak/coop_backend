package com.netegreek.chattr.responses.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import io.dropwizard.jersey.errors.ErrorMessage;

public class UsernameAlreadyTakenException extends WebApplicationException {

	public UsernameAlreadyTakenException() {
		super(Response.status(422).entity(
				new ErrorMessage(422, "Username already taken.")).build());
	}
}
