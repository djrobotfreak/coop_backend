package com.netegreek.chattr.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.codahale.metrics.annotation.Timed;
import com.netegreek.chattr.api.User;
import com.netegreek.chattr.auth.JWTUtil;
import com.netegreek.chattr.resources.requests.UserRequest;
import com.netegreek.chattr.resources.requests.credential.CredentialRequest;
import com.netegreek.chattr.responses.LoginResponse;
import com.netegreek.chattr.models.services.AuthenticationService;


@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

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
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginResponse register(UserRequest userRequest) {
		User user = authenticationService.register(userRequest);
		return new LoginResponse(jwtUtil.createJWT(user));
	}

	@POST
	@Timed
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginResponse login(CredentialRequest credentialRequest) {
		User user = authenticationService.login(credentialRequest);
		return new LoginResponse(jwtUtil.createJWT(user));
	}
}