package com.netegreek.chattr.services;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amazonaws.util.json.Jackson;
import com.netegreek.chattr.responses.FacebookTokenResponse;

/**
 * Created by Derek on 2/29/16.
 */
public class FacebookTokenService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookTokenService.class);
	private static final String CLIENT_ID = "228671707475003";
	private static final String CLIENT_SECRET = "b73febb6f94c991a90e78da47b35c379";

	private Client client;

	@Inject
	public FacebookTokenService(Client client) {
		this.client = client;
	}

    public String getApplicationToken(String userCredentials) {
        Response response = client.target("https://graph.facebook.com/v2.5/oauth/access_token")
				.queryParam("client_id", CLIENT_ID)
				.queryParam("client_secret", CLIENT_SECRET)
				.queryParam("grant_type", "fb_exchange_token")
				.queryParam("fb_exchange_token", userCredentials)
				.request().buildGet().invoke();

		LOGGER.info("Response headers: " + response.getHeaders().toString());
		LOGGER.info("Response status: " +response.getStatus());


		if (response.hasEntity()) {
			FacebookTokenResponse tokenResponse = response.readEntity(FacebookTokenResponse.class);
			return tokenResponse.getLongAccessToken();

		} else {
			throw new WebApplicationException("Facebook Rejected giving new token");
		}
    }
}
