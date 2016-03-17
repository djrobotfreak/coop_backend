package com.netegreek.chattr.resources.requests.credential;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
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