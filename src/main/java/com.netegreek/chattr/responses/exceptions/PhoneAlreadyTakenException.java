package com.netegreek.chattr.responses.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import io.dropwizard.jersey.errors.ErrorMessage;

/**
 * Created by Derek on 3/17/16.
 */
public class PhoneAlreadyTakenException extends WebApplicationException {

	public PhoneAlreadyTakenException(){

		super(Response.status(422).entity(
				new ErrorMessage(422, "Phone already taken.")).build());
	}
}
