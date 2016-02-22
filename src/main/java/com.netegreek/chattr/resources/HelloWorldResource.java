package com.netegreek.chattr.resources;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

    public HelloWorldResource() {
    }

    @GET
    public String sayHello() {
		return "Hello. It's me.";
    }
}
