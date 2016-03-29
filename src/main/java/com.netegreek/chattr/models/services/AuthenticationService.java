package com.netegreek.chattr.models.services;

import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import com.netegreek.chattr.api.User;
import com.netegreek.chattr.auth.BadJWTException;
import com.netegreek.chattr.auth.BasicCredentials;
import com.netegreek.chattr.api.UserCredentials;
import com.netegreek.chattr.models.db.UserEntity;
import com.netegreek.chattr.repositories.UserRepository;
import com.netegreek.chattr.resources.requests.UserRequest;
import com.netegreek.chattr.resources.requests.credential.CredentialRequest;
import com.netegreek.chattr.resources.requests.credential.FacebookCredentialRequest;
import com.netegreek.chattr.resources.requests.credential.GoogleCredentialRequest;
import com.netegreek.chattr.resources.requests.credential.PasswordCredentialRequest;
import com.netegreek.chattr.responses.exceptions.LoginFailedException;
import com.netegreek.chattr.responses.exceptions.PhoneAlreadyTakenException;
import com.netegreek.chattr.responses.exceptions.RegistrationRequiredException;
import com.netegreek.chattr.responses.exceptions.UserAlreadyExistsException;
import com.netegreek.chattr.responses.exceptions.UsernameAlreadyTakenException;
import org.eclipse.jetty.server.Response;

public class AuthenticationService {

	private UserRepository userRepository;

	private FacebookService facebookService;

	private GoogleService googleService;

	@Inject
	public AuthenticationService(UserRepository userRepository, FacebookService facebookService,
								 GoogleService googleService) {
		this.userRepository = userRepository;
		this.facebookService = facebookService;
		this.googleService = googleService;
	}

	public User register(UserRequest userRequest) {
		if (userRepository.getByUsername(userRequest.getUsername().toLowerCase()).isPresent()) {
			throw new UsernameAlreadyTakenException();
		} else if (userRepository.getByPhone(userRequest.getPhone()).isPresent()) {
			throw new PhoneAlreadyTakenException();
		}

		User user = mapUserFromUserRequest(userRequest);
		UserEntity userEntity = new UserEntity();
		user.updateEntity(userEntity);
		userRepository.save(userEntity);
		user.updateFromEntity(userEntity);
		return user;
	}

	public User login(CredentialRequest credentialRequest) {
		Optional<UserEntity> userEntity = getUserFromCredentials(credentialRequest);
		if (!userEntity.isPresent()) {
			throw new WebApplicationException(Response.SC_UNAUTHORIZED);
		}
		User user = new User();
		user.updateFromEntity(userEntity.get());
		return user;
	}

	private Optional<UserEntity> getUserFromCredentials(CredentialRequest credentialRequest) {
		if (credentialRequest.getClass() == FacebookCredentialRequest.class) {
			return getUserFromFacebookCredentials((FacebookCredentialRequest) credentialRequest);
		} else if (credentialRequest.getClass() == GoogleCredentialRequest.class) {
			return getUserFromGoogleCredentials((GoogleCredentialRequest) credentialRequest);
		} else if (credentialRequest.getClass() == PasswordCredentialRequest.class) {
			return getUserFromPasswordCredentials((PasswordCredentialRequest) credentialRequest);
		} else {
			throw new WebApplicationException("No UserCredentials Found");
		}
	}

	private UserCredentials registerCredentials(CredentialRequest credentialRequest) {

		if (credentialRequest.getClass() == FacebookCredentialRequest.class) {
			return registerWithFacebook((FacebookCredentialRequest) credentialRequest);
		} else if (credentialRequest.getClass() == GoogleCredentialRequest.class) {
			return registerWithGoogle((GoogleCredentialRequest) credentialRequest);
		} else if (credentialRequest.getClass() == PasswordCredentialRequest.class) {
			return registerWithPasswordCredentials((PasswordCredentialRequest) credentialRequest);
		} else {
			throw new WebApplicationException("No UserCredentials Found");
		}

	}

	private User mapUserFromUserRequest(UserRequest userRequest) {
		User user = new User();

		if (userRequest.getEmail().isPresent()) {
			user.setEmail(userRequest.getEmail().get());
		}

		user.setName(userRequest.getName());
		user.setUsername(userRequest.getUsername().toLowerCase());

		user.setPhone(userRequest.getPhone());
		if (userRequest.getPhotoUrl().isPresent()) {
			user.setPhotoUrl(userRequest.getPhotoUrl().get());
		}

		user.setUserCredentials(registerCredentials(userRequest.getCredentialRequest()));

		return user;
	}

	private boolean areFacebookCredentialsValid(FacebookCredentialRequest credentialRequest) {
		return (facebookService.checkCredentials(credentialRequest.getFacebookId(),
				credentialRequest.getFacebookToken()));
	}

	private boolean arePasswordCredentialsValid(String checkPassword, String storedPassword) {
		return BasicCredentials.checkPassword(checkPassword,
					storedPassword);
	}

	private Optional<UserEntity> getUserFromFacebookCredentials(FacebookCredentialRequest credentialRequest) {
		if (!areFacebookCredentialsValid(credentialRequest)) {
			throw new LoginFailedException();
		}
		Optional<UserEntity> userEntity = userRepository.getByFacebookId(credentialRequest.getFacebookId());
		if (!userEntity.isPresent()) {
			throw new RegistrationRequiredException();
		}
		return userEntity;
	}

	private Optional<UserEntity> getUserFromGoogleCredentials(GoogleCredentialRequest credentialRequest) {
		String googleId;

		try {
			googleId = googleService.getIdFromToken(credentialRequest.getGoogleToken());
		} catch (BadJWTException ex) {
			throw new LoginFailedException();
		}

		Optional<UserEntity> userEntity = userRepository.getByGoogleId(googleId);

		if (!userEntity.isPresent()) {
			throw new RegistrationRequiredException();
		}
		return userEntity;
	}

	private Optional<UserEntity> getUserFromPasswordCredentials(PasswordCredentialRequest credentialRequest) {
		Optional<UserEntity> userEntity = userRepository.getByUsername(credentialRequest.getUsername());
		if (!userEntity.isPresent()) {
			return Optional.empty();
		}
		if (!arePasswordCredentialsValid(credentialRequest.getPassword(), userEntity.get().getHashedPassword())) {
			return Optional.empty();
		}
		return userEntity;
	}

	private UserCredentials registerWithFacebook(FacebookCredentialRequest credentialRequest) {
		if (!areFacebookCredentialsValid(credentialRequest)) {
			throw new WebApplicationException("Facebook User ID and Token do not match");

		} else if (userRepository.getByFacebookId(credentialRequest.getFacebookId()).isPresent()) {
			throw new UserAlreadyExistsException();
		}

		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setFacebookId(Optional.of(credentialRequest.getFacebookId()));
		return userCredentials;
	}

	private UserCredentials registerWithGoogle(GoogleCredentialRequest credentialRequest) {
		UserCredentials userCredentials = new UserCredentials();
		String googleId;
		try {
			googleId = googleService.getIdFromToken(credentialRequest.getGoogleToken());
		} catch(BadJWTException ex) {
			throw new LoginFailedException();
		}
		userCredentials.setGoogleId(Optional.of(googleId));
		if (userRepository.getByGoogleId(googleId).isPresent()) {
			throw new UserAlreadyExistsException();
		}
		return userCredentials;
	}

	private UserCredentials registerWithPasswordCredentials(PasswordCredentialRequest credentialRequest) {
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setHashedPassword(Optional.of(BasicCredentials.hashPassword(credentialRequest.getPassword())));
		return userCredentials;
	}
}
