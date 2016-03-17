package com.netegreek.chattr.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import com.codahale.metrics.annotation.Timed;
import com.netegreek.chattr.api.User;
import com.netegreek.chattr.auth.JWTUtil;
import com.netegreek.chattr.resources.requests.UserRequest;
import com.netegreek.chattr.resources.requests.credential.CredentialRequest;
import com.netegreek.chattr.responses.LoginResponse;
import com.netegreek.chattr.models.services.AuthenticationService;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationResource.class);

	private AuthenticationService authenticationService;
	private JWTUtil jwtUtil;

    @Inject
    public AuthenticationResource(AuthenticationService authenticationService,
								  JWTUtil jwtUtil) {
        this.authenticationService = authenticationService;
		this.jwtUtil = jwtUtil;
    }

	@POST
	@Timed
	@Path("/register")
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginResponse register(UserRequest userRequest) {
		User user = authenticationService.register(userRequest);
		if (jwtUtil == null) {
			LOGGER.error("stupid jwtUtil is null for some dumb reason");
			throw new WebApplicationException("stupid jwtUtil is null for some dumb reason");
		} else if (user == null) {
			LOGGER.error("stupid user is null for some dumb reason");
			throw new WebApplicationException("stupid user is null for some dumb reason");
		}
		String jwtResponse = jwtUtil.createJWT(user);
		if (jwtResponse == null) {
			LOGGER.error("stupid jwtResponse is null for some dumb reason");
			throw new WebApplicationException("stupid jwtResponse is null for some dumb reason");
		}
		return new LoginResponse(jwtResponse);
	}

	@POST
	@Timed
	@Path("/login")
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginResponse login(CredentialRequest credentialRequest) {
		User user = authenticationService.login(credentialRequest);
		return new LoginResponse(jwtUtil.createJWT(user));
	}
}