package com.netegreek.chattr.resources.requests.credential;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

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
