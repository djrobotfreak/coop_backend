package com.netegreek.chattr.application.config;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dwene on 3/15/16.
 */
public class AuthenticationConfiguration {

	private final String secret;

	@JsonCreator
	public AuthenticationConfiguration(@NotNull @JsonProperty("secret") String secret) {
		this.secret = secret;
	}

	public String getSecret() {
		return secret;
	}


}
