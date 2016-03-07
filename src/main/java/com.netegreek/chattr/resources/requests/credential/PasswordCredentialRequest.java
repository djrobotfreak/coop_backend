package com.netegreek.chattr.resources.requests.credential;

import javax.validation.constraints.NotNull;

/**
 * Created by dwene on 3/6/16.
 */
public class PasswordCredentialRequest extends CredentialRequest {

	@NotNull
	private String password;

	private String username;

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
}
