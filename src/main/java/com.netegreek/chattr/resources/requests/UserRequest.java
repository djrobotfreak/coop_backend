package com.netegreek.chattr.resources.requests;

import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netegreek.chattr.resources.requests.credential.CredentialRequest;


public class UserRequest {

	@NotEmpty
	@Length(max = 32)
	private String name;

    @JsonProperty("photo_url")
	private Optional<String> photoUrl = Optional.empty();

	@NotEmpty
	@Length(max = 16)
    @JsonProperty("username")
	private String username;


	private Optional<String> email = Optional.empty();

	@NotEmpty
	private String phone;

	@NotNull
	@Valid
	@JsonProperty("credentials")
	private CredentialRequest credentialRequest;

	public String getName() {
		return name;
	}

	public Optional<String> getPhotoUrl() {
		return photoUrl;
	}

	public String getUsername() {
		return username;
	}

	public Optional<String> getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public CredentialRequest getCredentialRequest() {
		return credentialRequest;
	}
}
