package com.netegreek.chattr.resources;

import com.codahale.metrics.annotation.Timed;
import com.netegreek.chattr.db.User;
import com.netegreek.chattr.db.UserCredentials;
import com.netegreek.chattr.resources.requests.UserRequest;
import com.netegreek.chattr.resources.requests.credential.CredentialRequest;
import com.netegreek.chattr.services.AuthenticationService;
import com.netegreek.chattr.services.FacebookService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


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
	public User register(UserRequest userRequest) {
		User user = authenticationService.register(userRequest);
		return user;
	}

	@POST
	@Timed
	@Path("/login")
	public String login(CredentialRequest credentialRequest) {
		User user = authenticationService.login(credentialRequest);
		return user.getToken();
	}
}