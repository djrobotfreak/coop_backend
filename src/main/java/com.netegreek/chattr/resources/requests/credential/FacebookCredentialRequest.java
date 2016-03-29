package com.netegreek.chattr.resources.requests.credential;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookCredentialRequest extends CredentialRequest {

	@NotBlank
	@JsonProperty("id")
	private Long facebookId;

	@NotBlank
	@JsonProperty("token")
	private String facebookToken;

	public Long getFacebookId() {
		return facebookId;
	}

	public String getFacebookToken() {
		return facebookToken;
	}
}
