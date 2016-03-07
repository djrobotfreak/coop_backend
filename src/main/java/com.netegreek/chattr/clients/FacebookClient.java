package com.netegreek.chattr.clients;

import com.netegreek.chattr.responses.FacebookTokenResponse;
import com.netegreek.chattr.responses.FacebookUserResponse;
import org.glassfish.jersey.client.ClientResponse;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

/**
 * Created by Derek on 2/29/16.
 */
public class FacebookClient {

    private static final String CLIENT_ID = "228671707475003";
    private static final String CLIENT_SECRET = "b73febb6f94c991a90e78da47b35c379";
    private static final String GRAPH_API_HOST = "https://graph.facebook.com/v2.5/";

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

        if (response.getStatus() != 200) {
            handleFacebookErrors(response);
        }

        if (response.hasEntity()) {
            FacebookTokenResponse tokenResponse = response.readEntity(FacebookTokenResponse.class);
            return tokenResponse.getLongAccessToken();

        } else {
            throw new WebApplicationException("No entity received from Facebook.");
        }
    }

    public FacebookUserResponse getUser(String accessToken) {
        Response response = client.target(GRAPH_API_HOST + "me")
                .queryParam("access_token", accessToken)
                .queryParam("fields", "id,name")
                .request()
                .get();
        if (response.getStatus() != 200) {
            handleFacebookErrors(response);
        }

        if (response.hasEntity()) {
            return response.readEntity(FacebookUserResponse.class);

        } else {
            throw new WebApplicationException("Unknown Exception getting new User.");
        }
    }

    //TODO: This is pretty hacky. Really we need to have different exceptions that handle all this.
    private void handleFacebookErrors(Response response) {
        String s = response.readEntity(String.class);
        throw new WebApplicationException("Error recieved from facebook: " + s);
    }
}
