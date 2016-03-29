package com.netegreek.chattr.resources.requests.credential;

import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordCredentialRequest extends CredentialRequest {

	@NotBlank
	private String password;

	@NotBlank
	private String username;

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

}