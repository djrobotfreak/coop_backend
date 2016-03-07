package com.netegreek.chattr.resources.requests.credential;

import javax.validation.constraints.NotNull;

/**
 * Created by dwene on 3/6/16.
 */
public class FacebookCredentialRequest extends CredentialRequest {

	@NotNull
	private Long facebookId;

	@NotNull
	private String facebookToken;

	public Long getFacebookId() {
		return facebookId;
	}

	public String getFacebookToken() {
		return facebookToken;
	}
}
