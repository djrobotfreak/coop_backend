package com.netegreek.chattr.resources;

import java.net.URI;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.codahale.metrics.annotation.Timed;
import com.netegreek.chattr.db.User;
import com.netegreek.chattr.resources.requests.UserRequest;
import com.netegreek.chattr.resources.requests.credential.CredentialRequest;
import com.netegreek.chattr.responses.LoginResponse;
import com.netegreek.chattr.services.AuthenticationService;


@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

	private AuthenticationService authenticationService;

    @Inject
    public AuthenticationResource(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

	@POST
	@Timed
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginResponse register(UserRequest userRequest) {
		User user = authenticationService.register(userRequest);
		return new LoginResponse(user.getToken());
	}

	@POST
	@Timed
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginResponse login(CredentialRequest credentialRequest) {

		User user = authenticationService.login(credentialRequest);
		return new LoginResponse(user.getToken());
	}
}