package com.netegreek.chattr.services;

import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import com.netegreek.chattr.auth.BasicCredentials;
import com.netegreek.chattr.db.User;
import com.netegreek.chattr.db.UserCredentials;
import com.netegreek.chattr.repositories.UserRepository;
import com.netegreek.chattr.resources.requests.UserRequest;
import com.netegreek.chattr.resources.requests.credential.CredentialRequest;
import com.netegreek.chattr.resources.requests.credential.PasswordCredentialRequest;
import com.netegreek.chattr.resources.requests.credential.FacebookCredentialRequest;
import com.netegreek.chattr.resources.requests.credential.GoogleCredentialRequest;
import com.netegreek.chattr.responses.ErrorResponses;


public class AuthenticationService {

	private UserRepository userRepository;

	private FacebookService facebookService;

	@Inject
	public AuthenticationService(UserRepository userRepository, FacebookService facebookService) {
		this.userRepository = userRepository;
		this.facebookService = facebookService;
	}

	public User register(UserRequest userRequest) {
		if (userRepository.getByUsername(userRequest.getUsername()).isPresent()) {
			throw new WebApplicationException(ErrorResponses.USERNAME_ALREADY_TAKEN);
		} else if (userRepository.getByPhone(userRequest.getPhone()).isPresent()) {
			throw new WebApplicationException(ErrorResponses.PHONE_ALREADY_TAKEN);
		}

		User user = mapUserFromUserRequest(userRequest);
		userRepository.save(user);
		return user;
	}


	public User login(CredentialRequest credentialRequest) {
		User user = getUserFromCredentials(credentialRequest);
		user.setToken("abcdefg");
		userRepository.save(user);
		return user;
	}

	private User getUserFromCredentials(CredentialRequest credentialRequest) {
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
			if (userRepository.getByFacebookId(((GoogleCredentialRequest)credentialRequest).getGoogleId()).isPresent()) {
				throw new WebApplicationException(ErrorResponses.USER_ALREADY_EXISTS);
			}
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

		if (userRequest.getPhotoUrl().isPresent()) {
			user.setPhotoUrl(userRequest.getPhotoUrl().get());
		}

		user.setUsername(userRequest.getUsername());
		user.setUserCredentials(registerCredentials(userRequest.getCredentialRequest()));

		return user;
	}

	private boolean areFacebookCredentialsValid(FacebookCredentialRequest credentialRequest) {
		return (facebookService.checkCredentials(credentialRequest.getFacebookId(),
				credentialRequest.getFacebookToken()));
	}

	private boolean areGoogleCredentialsValid(GoogleCredentialRequest credentialRequest) {
		return true;
	}

	private boolean arePasswordCredentialsValid(PasswordCredentialRequest credentialRequest) {
		Optional<User> user =  userRepository.getByUsername(credentialRequest.getUsername());
		if (user.isPresent()) {
			if (user.get().getUserCredentials().getHashedPassword().isPresent()) {
				return BasicCredentials.checkPassword(credentialRequest.getPassword(),
						user.get().getUserCredentials().getHashedPassword().get());
			}

		}
		return false;
	}

	private User getUserFromFacebookCredentials(FacebookCredentialRequest credentialRequest) {
		if (!areFacebookCredentialsValid(credentialRequest)) {
			throw new WebApplicationException(ErrorResponses.LOGIN_FAILED);
		}
		Optional<User> user = userRepository.getByFacebookId(credentialRequest.getFacebookId());
		if (!user.isPresent()) {
			throw new WebApplicationException(ErrorResponses.REGISTRATION_REQUIRED);
		}
		return user.get();
	}


	private User getUserFromGoogleCredentials(GoogleCredentialRequest credentialRequest) {
		if (!areGoogleCredentialsValid(credentialRequest)) {
			throw new WebApplicationException(ErrorResponses.LOGIN_FAILED);
		}
		Optional<User> user = userRepository.getByGoogleId(credentialRequest.getGoogleId());
		if (!user.isPresent()) {
			throw new WebApplicationException(ErrorResponses.REGISTRATION_REQUIRED);
		}
		return user.get();
	}

	private User getUserFromPasswordCredentials(PasswordCredentialRequest credentialRequest) {
		if (!arePasswordCredentialsValid(credentialRequest)) {
			throw new WebApplicationException(ErrorResponses.LOGIN_FAILED);
		}
		Optional<User> user = userRepository.getByUsername(credentialRequest.getUsername());
		if (!user.isPresent()) {
			throw new WebApplicationException(ErrorResponses.LOGIN_FAILED);
		}
		return user.get();
	}


	private UserCredentials registerWithFacebook(FacebookCredentialRequest credentialRequest) {

		if (!areFacebookCredentialsValid(credentialRequest)) {
			throw new WebApplicationException("Facebook User ID and Token do not match");

		} else if (userRepository.getByFacebookId(credentialRequest.getFacebookId()).isPresent()) {
			throw new WebApplicationException(ErrorResponses.USER_ALREADY_EXISTS);
		}

		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setFacebookId(Optional.of(credentialRequest.getFacebookId()));
		return userCredentials;
	}

	private UserCredentials registerWithGoogle(GoogleCredentialRequest credentialRequest) {
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setGoogleId(Optional.of(credentialRequest.getGoogleId()));
		return userCredentials;
	}

	private UserCredentials registerWithPasswordCredentials(PasswordCredentialRequest credentialRequest) {

		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setHashedPassword(Optional.of(BasicCredentials.hashPassword(credentialRequest.getPassword())));
		return userCredentials;
	}
}
