package com.netegreek.chattr.resources.requests.credential;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordCredentialRequest extends CredentialRequest {

	@NotEmpty
	@Length(min = 6, max = 32)
	private String password;

	@NotEmpty
	@Length(min = 5, max = 16)
	private String username;

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

}