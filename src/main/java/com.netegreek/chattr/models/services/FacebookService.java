package com.netegreek.chattr.models.services;

import javax.inject.Inject;
import com.netegreek.chattr.clients.FacebookClient;
import com.netegreek.chattr.models.mappers.FacebookUserMapper;
import com.netegreek.chattr.repositories.UserRepository;
import com.netegreek.chattr.responses.FacebookUserResponse;

/**
 * Created by dwene on 3/5/16.
 */
public class FacebookService {

    private FacebookClient facebookClient;

    @Inject
    public FacebookService(FacebookClient facebookClient) {
        this.facebookClient = facebookClient;
    }

	public boolean checkCredentials(Long id, String accessToken) {
		FacebookUserResponse userResponse = facebookClient.getUser(accessToken);
		return id.equals(userResponse.getId());
	}
}
