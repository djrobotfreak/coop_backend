package com.netegreek.chattr.resources.requests.credential;

import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookCredentialRequest extends CredentialRequest {

	@NotEmpty
	@JsonProperty("id")
	private Long facebookId;

	@NotEmpty
	@JsonProperty("token")
	private String facebookToken;

	public Long getFacebookId() {
		return facebookId;
	}

	public String getFacebookToken() {
		return facebookToken;
	}
}
