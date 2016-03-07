package com.netegreek.chattr.responses;

import javax.ws.rs.core.Response;
import io.dropwizard.jersey.errors.ErrorMessage;

/**
 * Created by dwene on 3/6/16.
 */
public class ErrorResponses {

	public final static Response PHONE_ALREADY_TAKEN = Response.status(422).entity(
			new ErrorMessage(422, "Phone already taken.")).build();

	public final static Response USERNAME_ALREADY_TAKEN = Response.status(422).entity(
			new ErrorMessage(422, "Username already taken.")).build();

	public final static Response USER_ALREADY_EXISTS = Response.status(409).entity(
			new ErrorMessage(409, "Username already taken.")).build();

	public final static Response LOGIN_FAILED = Response.status(403).entity(
			new ErrorMessage(403, "Invalid Login Credentials")).build();

	public final static Response REGISTRATION_REQUIRED = Response.status(307).entity(
			new ErrorMessage(303, "Authentication successful but user not found, " +
					"registration with app required")).build();
}
