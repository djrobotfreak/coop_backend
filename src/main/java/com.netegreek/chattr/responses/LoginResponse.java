package com.netegreek.chattr.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Derek on 3/7/16.
 */
public class LoginResponse {

    @JsonProperty("auth_token")
    private String authToken;

    public LoginResponse(String authToken) {
        this.authToken = authToken;
    }

	@JsonProperty("auth_token")
    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
