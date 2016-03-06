package com.netegreek.chattr.mappers;

import com.netegreek.chattr.db.User;
import com.netegreek.chattr.responses.FacebookUserResponse;

import javax.inject.Inject;

/**
 * Created by dwene on 3/5/16.
 */
public class FacebookUserMapper implements ResponseMapper<User, FacebookUserResponse> {

    @Inject
    public FacebookUserMapper() {
    }

    @Override
    public User createValueTFromResponse(FacebookUserResponse facebookUserResponse) {
        User user = new User();
        user.setEmail(facebookUserResponse.getEmail());
        user.setFacebookId(facebookUserResponse.getId());
        user.setName(facebookUserResponse.getName());
        return user;
    }

    public User updateValueTFromResponse(User user, FacebookUserResponse facebookUserResponse) {
        user.setEmail(facebookUserResponse.getEmail());
        user.setFacebookId(facebookUserResponse.getId());
        user.setName(facebookUserResponse.getName());
        return user;
    }
}
