package com.netegreek.chattr.resources.requests.credential;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dwene on 3/6/16.
 */
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
