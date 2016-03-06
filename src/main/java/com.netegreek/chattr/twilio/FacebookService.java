package com.netegreek.chattr.twilio;

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

    public User registerOrLogin(String shortAccessToken) {
        String longAccessToken = facebookClient.getLongAccessToken(shortAccessToken);
        FacebookUserResponse userResponse = facebookClient.getUser(longAccessToken);
        Optional<User> userOptional = userRepository.getByFBID(userResponse.getId());
        User user;

        if (userOptional.isPresent()) {
            user = userMapper.updateValueTFromResponse(userOptional.get(), userResponse);
        } else {
            user = userMapper.createValueTFromResponse(userResponse);
            user.setId(UUID.randomUUID());
        }
        userRepository.save(user);

        return user;
    }
}
