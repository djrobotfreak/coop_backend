package com.netegreek.chattr.services;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.inject.Inject;

/**
 * Created by Derek on 2/29/16.
 */
public class FacebookTokenService {


    @Inject
    public String getApplicationToken(String facebookToken) {
        JerseyClient client = JerseyClientBuilder.createClient();
    }
}
