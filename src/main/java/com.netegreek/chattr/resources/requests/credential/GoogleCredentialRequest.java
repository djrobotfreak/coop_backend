package com.netegreek.chattr.resources.requests.credential;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleCredentialRequest extends CredentialRequest {

	@NotBlank
	@JsonProperty("token")
	private String googleToken;

	public String getGoogleToken() {
		return googleToken;
	}
}
