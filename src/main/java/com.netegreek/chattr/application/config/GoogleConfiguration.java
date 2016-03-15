package com.netegreek.chattr.application.config;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dwene on 3/15/16.
 */
public class GoogleConfiguration {

	@JsonProperty("client_id")
	@NotNull
	private String clientId;

	@NotNull
	private String secret;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}
