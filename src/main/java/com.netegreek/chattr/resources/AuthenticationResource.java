package com.netegreek.chattr.resources;

import com.netegreek.chattr.repositories.UserRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;


@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    private UserRepository userRepository;

    public AuthenticationResource(UserRepository userRepository, Client client) {
        this.userRepository = userRepository;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    private String login(String shortFacebookToken) {

        return "";
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    private User register(String shortFacebookToken) {
//
//    }
}
