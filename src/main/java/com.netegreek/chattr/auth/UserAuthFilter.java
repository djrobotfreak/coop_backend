package com.netegreek.chattr.auth;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import java.io.IOException;
import java.security.Principal;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.base.Optional;
import com.netegreek.chattr.api.BasicUser;
import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.AuthenticationException;
import io.jsonwebtoken.ExpiredJwtException;


public class UserAuthFilter<P extends Principal>  extends AuthFilter<BasicUser, P> {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthFilter.class);

	private JWTUtil jwtUtil;

	@Inject
	public UserAuthFilter(JWTUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}

	public void filter(ContainerRequestContext requestContext) throws IOException {

		Optional<BasicUser> userOptional = getUserFromHeaders(requestContext);

		if (!userOptional.isPresent()) {
			return;
		}
		try {
			final Optional<P> user = this.authenticator.authenticate(userOptional.get());

			if (user.isPresent()) {
				requestContext.setSecurityContext(new SecurityContext() {

					public Principal getUserPrincipal() {
						return user.get();
					}

					public boolean isUserInRole(String role) {
						return authorizer.authorize(user.get(), role);
					}

					public boolean isSecure() {
						return requestContext.getSecurityContext().isSecure();
					}

					public String getAuthenticationScheme() {
						return SecurityContext.BASIC_AUTH;
					}
				});
			} else {
				throw new WebApplicationException(Response.status(UNAUTHORIZED).entity("Unable to determine user from cookie.").build());

			}
		} catch (AuthenticationException e) {
			LOGGER.warn("Error Authenticating cookie credentials", e);
			throw new InternalServerErrorException();
		}
	}

	private Optional<BasicUser> getUserFromHeaders(ContainerRequestContext requestContext) {

		String access_token = requestContext.getHeaderString("Coop-Authorization");

		if (access_token == null) {
			requestContext.abortWith(
					Response.status(UNAUTHORIZED).entity("Credentials are required to access this resource.").build());
			return Optional.absent();
		}

		BasicUser user;

		try {
			user = jwtUtil.getIdByJWT(access_token);
		} catch (BadJWTException ex) {
			requestContext.abortWith(
					Response.status(UNAUTHORIZED).entity("Credentials are required to access this resource.").build());
			return Optional.absent();
		} catch (ExpiredJwtException ex) {
			requestContext.abortWith(
					Response.status(UNAUTHORIZED).entity("Credentials are expired").build());
			return Optional.absent();
		}
		return Optional.of(user);
	}

	public static class Builder<P extends Principal> extends AuthFilterBuilder<BasicUser, P, UserAuthFilter<P>> {

		private JWTUtil jwtUtil;

		public Builder(JWTUtil jwtUtil) {
			this.jwtUtil = jwtUtil;
		}

		protected UserAuthFilter<P> newInstance() {
			return new UserAuthFilter<>(jwtUtil);
		}
	}
}
