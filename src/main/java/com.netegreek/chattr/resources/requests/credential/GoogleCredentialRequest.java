package com.netegreek.chattr.resources.requests.credential;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleCredentialRequest extends CredentialRequest {

	@NotNull
	@JsonProperty("token")
	private String googleToken;

	public String getGoogleToken() {
		return googleToken;
	}
}
