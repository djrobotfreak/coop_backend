package com.netegreek.chattr.resources.requests.credential;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by dwene on 3/6/16.
 */
public class GoogleCredentialRequest extends CredentialRequest {

	@NotNull
	@JsonProperty("id")
	private Long googleId;

	@NotNull
	@JsonProperty("token")
	private String googleToken;

	public Long getGoogleId() {
		return googleId;
	}

	public String getGoogleToken() {
		return googleToken;
	}
}
