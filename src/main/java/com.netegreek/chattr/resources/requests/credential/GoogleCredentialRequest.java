package com.netegreek.chattr.resources.requests.credential;

import javax.validation.constraints.NotNull;

/**
 * Created by dwene on 3/6/16.
 */
public class GoogleCredentialRequest extends CredentialRequest {

	@NotNull
	private Long googleId;

	@NotNull
	private String googleToken;

	public Long getGoogleId() {
		return googleId;
	}

	public String getGoogleToken() {
		return googleToken;
	}
}
