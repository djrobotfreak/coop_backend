package com.netegreek.chattr.responses.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import io.dropwizard.jersey.errors.ErrorMessage;

/**
 * Created by Derek on 3/17/16.
 */
public class LoginFailedException extends WebApplicationException {

	public LoginFailedException() {
		super(Response.status(403).entity(
				new ErrorMessage(403, "Invalid Login Credentials")).build());
	}
}
