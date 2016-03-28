package com.netegreek.chattr.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import com.netegreek.chattr.models.services.UserService;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/api/user")
public class UserResource {

    private UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/username/{username}")
	@UnitOfWork
    public Response checkUserNameAvailability(@PathParam("username") String username) {
        if (userService.isUsernameAvailable(username)) {
            return Response.ok().build();
        } else {
            return Response.status(409).build();
        }
    }

    @GET
    @Path("/phone/{phone}")
	@UnitOfWork
    public Response checkPhoneAvailability(@PathParam("phone") String phone) {
        if (userService.isPhoneAvailable(phone)) {
            return Response.ok().build();
        } else {
            return Response.status(409).build();
        }
    }
}
