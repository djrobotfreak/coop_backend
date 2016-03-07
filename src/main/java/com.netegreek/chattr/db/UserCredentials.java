package com.netegreek.chattr.db;

import java.util.Optional;

/**
 * Created by dwene on 3/6/16.
 */
public class UserCredentials {

	private Optional<String> username = Optional.empty();

	private Optional<String> hashedPassword = Optional.empty();

	private Optional<Long> googleId = Optional.empty();

	private Optional<Long> facebookId = Optional.empty();

	public Optional<String> getUsername() {
		return username;
	}

	public void setUsername(Optional<String> username) {
		this.username = username;
	}

	public Optional<String> getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(Optional<String> hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public Optional<Long> getGoogleId() {
		return googleId;
	}

	public void setGoogleId(Optional<Long> googleId) {
		this.googleId = googleId;
	}

	public Optional<Long> getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(Optional<Long> facebookId) {
		this.facebookId = facebookId;
	}
}
