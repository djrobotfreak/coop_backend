package com.netegreek.chattr.resources.requests.credential;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookCredentialRequest extends CredentialRequest {

	@NotNull
	@JsonProperty("id")
	private Long facebookId;

	@NotNull
	@JsonProperty("token")
	private String facebookToken;

	public Long getFacebookId() {
		return facebookId;
	}

	public String getFacebookToken() {
		return facebookToken;
	}
}
