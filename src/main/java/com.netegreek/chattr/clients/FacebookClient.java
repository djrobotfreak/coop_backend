package com.netegreek.chattr.clients;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.netegreek.chattr.db.User;
import com.netegreek.chattr.responses.FacebookTokenResponse;
import com.netegreek.chattr.responses.FacebookUserResponse;

/**
 * Created by Derek on 2/29/16.
 */
public class FacebookClient {

	private static final String CLIENT_ID = "228671707475003";
	private static final String CLIENT_SECRET = "b73febb6f94c991a90e78da47b35c379";
	private static final String GRAPH_API_HOST= "https://graph.facebook.com/v2.5/";

	private Client client;

	@Inject
	public FacebookClient(Client client) {
		this.client = client;
	}

    public String getLongAccessToken(String userCredentials) {
        Response response = client.target(GRAPH_API_HOST + "oauth/access_token")
				.queryParam("client_id", CLIENT_ID)
				.queryParam("client_secret", CLIENT_SECRET)
				.queryParam("grant_type", "fb_exchange_token")
				.queryParam("fb_exchange_token", userCredentials)
				.request()
				.get();

		if (response.hasEntity()) {
			FacebookTokenResponse tokenResponse = response.readEntity(FacebookTokenResponse.class);
			return tokenResponse.getLongAccessToken();

		} else {
			throw new WebApplicationException("Facebook rejected giving new token.");
		}
    }

	public FacebookUserResponse getUser(String accessToken) {
		Response response = client.target(GRAPH_API_HOST + "me")
				.queryParam("access_token", accessToken)
				.queryParam("fields", "id,name,timezone")
				.request()
				.get();

		if (response.hasEntity()) {
			FacebookUserResponse userResponse = response.readEntity(FacebookUserResponse.class);
			return userResponse;

		} else {
			throw new WebApplicationException("Unknown Exception getting new User.");
		}
	}
}
