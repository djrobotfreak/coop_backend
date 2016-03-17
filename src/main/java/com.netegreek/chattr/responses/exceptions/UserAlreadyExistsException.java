package com.netegreek.chattr.responses.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import io.dropwizard.jersey.errors.ErrorMessage;

/**
 * Created by Derek on 3/17/16.
 */
public class UserAlreadyExistsException extends WebApplicationException {

	public UserAlreadyExistsException() {
		super(Response.status(409).entity(
				new ErrorMessage(409, "Username already taken.")).build());
	}
}
