package com.netegreek.chattr.resources;

import com.codahale.metrics.annotation.Timed;
import com.netegreek.chattr.repositories.UserRepository;
import com.netegreek.chattr.services.FacebookTokenService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;


@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

	private FacebookTokenService facebookTokenService;

	@Inject
    public AuthenticationResource(FacebookTokenService facebookTokenService) {
		this.facebookTokenService = facebookTokenService;
    }

    @GET
	@Timed
    @Path("/{token}")
    public String login(@PathParam("token") String shortFacebookToken) {
		return facebookTokenService.getApplicationToken(shortFacebookToken);
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    private User register(String shortFacebookToken) {
//
//    }
}
