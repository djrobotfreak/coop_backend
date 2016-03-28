package com.netegreek.chattr.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path("/_ah/health")
public class HealthCheckResource {

	@GET
	public Response healthCheck(){
		return Response.ok().build();
	}
}
