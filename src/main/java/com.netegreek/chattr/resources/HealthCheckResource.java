package com.netegreek.chattr.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Derek on 3/23/16.
 */
@Path("health")
public class HealthCheckResource {

	@GET
	public Response healthCheck(){
		return Response.ok().build();
	}
}
