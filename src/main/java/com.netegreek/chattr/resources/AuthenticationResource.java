package com.netegreek.chattr.resources;

import com.codahale.metrics.annotation.Timed;
import com.netegreek.chattr.db.User;
import com.netegreek.chattr.services.FacebookService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    private FacebookService facebookService;

    @Inject
    public AuthenticationResource(FacebookService facebookService) {
        this.facebookService = facebookService;
    }

    @GET
    @Timed
    @Path("/facebook/{token}")
    public User loginWithFacebook(@PathParam("token") String shortFacebookToken) {
        return facebookService.registerOrLogin(shortFacebookToken);
    }

    //TODO: set this up with Google.
    @GET
    @Timed
    @Path("/google/{token}")
    public User loginWithGoogle(@PathParam("token") String shortGoogleToken) {
        return null;
    }
}