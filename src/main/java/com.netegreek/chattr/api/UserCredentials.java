package com.netegreek.chattr.api;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Optional;
import com.netegreek.chattr.models.db.UserEntity;

public class UserCredentials {

	private Optional<String> hashedPassword = Optional.empty();

	private Optional<String> googleId = Optional.empty();

	private Optional<Long> facebookId = Optional.empty();

	public Optional<String> getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(Optional<String> hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public Optional<String> getGoogleId() {
		return googleId;
	}

	public void setGoogleId(Optional<String> googleId) {
		this.googleId = googleId;
	}

	public Optional<Long> getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(Optional<Long> facebookId) {
		this.facebookId = facebookId;
	}

	public void updateEntity(UserEntity userEntity) {
		if(facebookId.isPresent()) {
			userEntity.setFacebookId(facebookId.get());
		}
		if (googleId.isPresent()) {
			userEntity.setGoogleId(googleId.get());
		}
		if (hashedPassword.isPresent()) {
			userEntity.setHashedPassword(hashedPassword.get());
		}
	}

	public void updateFromEntity(UserEntity entity) {
		if (entity.getFacebookId() != null) {
			facebookId = Optional.of(entity.getFacebookId());
		}
		if (entity.getGoogleId() != null) {
			googleId = Optional.of(entity.getGoogleId());
		}
		if (entity.getHashedPassword() != null) {
			hashedPassword = Optional.of(entity.getHashedPassword());
		}
	}
}
