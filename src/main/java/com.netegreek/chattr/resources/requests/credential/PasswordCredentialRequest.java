package com.netegreek.chattr.resources.requests.credential;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by dwene on 3/6/16.
 */
public class PasswordCredentialRequest extends CredentialRequest {

	@NotNull
	private String password;

	@JsonProperty("user_name")
	private String username;

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

}