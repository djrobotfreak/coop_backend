package com.netegreek.chattr.responses;

import org.joda.time.DateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dwene on 3/1/16.
 */
public class FacebookTokenResponse {

	@JsonProperty("access_token")
	private String longAccessToken;

	@JsonProperty("expires_in")
	private DateTime expiresIn;

	@JsonProperty("token_type")
	private String tokenType;

	public String getLongAccessToken() {
		return longAccessToken;
	}

	public void setLongAccessToken(String longAccessToken) {
		this.longAccessToken = longAccessToken;
	}

	public DateTime getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(DateTime expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

}
