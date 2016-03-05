package com.netegreek.chattr.twilio;

import java.util.UUID;
import javax.inject.Inject;
import com.netegreek.chattr.clients.FacebookClient;
import com.netegreek.chattr.db.User;
import com.netegreek.chattr.mappers.FacebookUserMapper;
import com.netegreek.chattr.repositories.UserRepository;
import com.netegreek.chattr.responses.FacebookUserResponse;

/**
 * Created by dwene on 3/5/16.
 */
public class FacebookService {

	private FacebookClient facebookClient;

	private FacebookUserMapper userMapper;

	private UserRepository userRepository;

	@Inject
	public FacebookService(FacebookClient facebookClient, FacebookUserMapper userMapper, UserRepository repository) {
		this.facebookClient = facebookClient;
		this.userMapper = userMapper;
	}

	public User registerOrLogin(String shortAccessToken) {
		return register(shortAccessToken);
	}


	public User register(String shortAccessToken) {

		String longAccessToken = facebookClient.getLongAccessToken(shortAccessToken);
		FacebookUserResponse userResponse = facebookClient.getUser(longAccessToken);
		User user = userMapper.createValueTFromResponse(userResponse);
		user.setId(UUID.randomUUID());
		userRepository.save(user);
		return user;

	}
}
