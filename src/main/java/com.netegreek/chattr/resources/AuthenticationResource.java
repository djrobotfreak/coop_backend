package com.netegreek.chattr.resources;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import com.codahale.metrics.annotation.Timed;
import com.netegreek.chattr.api.BasicUser;
import com.netegreek.chattr.api.User;
import com.netegreek.chattr.auth.JWTUtil;
import com.netegreek.chattr.resources.requests.UserRequest;
import com.netegreek.chattr.resources.requests.credential.CredentialRequest;
import com.netegreek.chattr.responses.LoginResponse;
import com.netegreek.chattr.models.services.AuthenticationService;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/api/auth")
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
	public LoginResponse register(@Valid UserRequest userRequest) {
		User user = authenticationService.register(userRequest);
		BasicUser basicUser = new BasicUser();
		basicUser.updateFromUser(user);
		return new LoginResponse(jwtUtil.createJWT(basicUser));
	}

	@POST
	@Timed
	@Path("/login")
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginResponse login(@Valid CredentialRequest credentialRequest) {
		User user = authenticationService.login(credentialRequest);
		BasicUser basicUser = new BasicUser();
		basicUser.updateFromUser(user);
		return new LoginResponse(jwtUtil.createJWT(basicUser));
	}
}