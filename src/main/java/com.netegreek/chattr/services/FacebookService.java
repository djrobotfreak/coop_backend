package com.netegreek.chattr.services;

import com.netegreek.chattr.clients.FacebookClient;
import com.netegreek.chattr.db.User;
import com.netegreek.chattr.mappers.FacebookUserMapper;
import com.netegreek.chattr.repositories.UserRepository;
import com.netegreek.chattr.responses.FacebookUserResponse;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

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
        this.userRepository = repository;
    }

	public boolean checkCredentials(Long id, String accessToken) {
		FacebookUserResponse userResponse = facebookClient.getUser(accessToken);
		return id.equals(userResponse.getId());
	}
}